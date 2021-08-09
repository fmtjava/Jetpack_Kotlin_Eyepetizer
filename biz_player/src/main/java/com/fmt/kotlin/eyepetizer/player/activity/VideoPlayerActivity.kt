package com.fmt.kotlin.eyepetizer.player.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.transition.Transition
import android.util.Log
import android.view.ViewGroup
import android.view.ViewParent
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.fmt.kotlin.eyepetizer.common.base.activity.BaseBindVMActivity
import com.fmt.kotlin.eyepetizer.common.ext.fromJson
import com.fmt.kotlin.eyepetizer.common.ext.getStatusBarHeight
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.player.R
import com.fmt.kotlin.eyepetizer.player.adapter.TransitionListenerAdapter
import com.fmt.kotlin.eyepetizer.player.adapter.VideoRelateAdapter
import com.fmt.kotlin.eyepetizer.player.databinding.PlayerActivityVideoBinding
import com.fmt.kotlin.eyepetizer.player.observer.JZVDObserver
import com.fmt.kotlin.eyepetizer.player.util.VideoWatchManager
import com.fmt.kotlin.eyepetizer.player.viewmodel.VideoPlayerViewModel
import com.fmt.kotlin.eyepetizer.provider.constant.BaseConstant
import com.fmt.kotlin.eyepetizer.provider.jzvd.JzvdStdRv
import com.fmt.kotlin.eyepetizer.provider.jzvd.ViewMoveHelper
import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.fmt.kotlin.eyepetizer.provider.jzvd.ViewAttr
import com.fmt.kotlin.eyepetizer.provider.model.event.VideoAutoPlayEvent
import com.fmt.kotlin.eyepetizer.provider.model.event.WatchVideoEvent
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import com.fmt.livedatabus.LiveDataBus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.player_activity_video.*

@AndroidEntryPoint
@Route(path = RouterPath.Video.PATH_VIDEO_HOME)
class VideoPlayerActivity : BaseBindVMActivity<VideoPlayerViewModel, PlayerActivityVideoBinding>() {

    private val DURATION: Long = 250

    private var mTransition: Transition? = null

    private lateinit var mCurrentAttr: ViewAttr

    private lateinit var videoModel: Data

    @JvmField
    @Autowired(name = BaseConstant.VIDEO_MODE_KEY)
    var videoModelJSON: String = ""

    @JvmField
    @Autowired(name = BaseConstant.VIDEO_IS_FROM_RELATE_KEY)
    var fromRelate: Boolean = false

    @Autowired(name = BaseConstant.VIDEO_IS_FROM_PLAYLIST_KEY)
    lateinit var viewAttr: ViewAttr

    private val mAdapter: VideoRelateAdapter by lazy { VideoRelateAdapter(this) }

    override val getLayoutRes: Int
        get() = R.layout.player_activity_video

    override fun initView() {
        immersionStatusBar(true, android.R.color.black, false, 0.2f)
        mSwipeRefreshLayout.setOnRefreshListener {
            getRelateVideoList()
        }
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.isNestedScrollingEnabled = false
        mRecyclerView.adapter = mAdapter
    }

    override fun showLoading() {
        mSwipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun initData() {
        ARouter.getInstance().inject(this)
        videoModel = fromJson(videoModelJSON)
        mBind.videoModel = videoModel
        VideoWatchManager.addVideoWatchRecord(videoModel)
        LiveDataBus.with<WatchVideoEvent>(BaseConstant.WATCH_VIDEO_EVENT)
            .setData(WatchVideoEvent())

        if (isInitViewAttr()) {//从自动播放页面点击进入时
            addVideoViewFromList()
        } else {
            addNormalVideoView()
            lifecycle.addObserver(JZVDObserver())
            if (fromRelate) {//从相关视频Item项点击进入
                getRelateVideoList()
            } else {//从首页/关注等页面点击进入，此时需要开启共享元素动画
                initTransition()
            }
        }
    }

    private fun addNormalVideoView() {
        mSurfaceContainer.viewTreeObserver
            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    mSurfaceContainer.viewTreeObserver.removeOnPreDrawListener(this)
                    val jzvdStd = JzvdStd(this@VideoPlayerActivity).apply {
                        setUp(videoModel.playUrl, videoModel.title)
                        startVideo()
                    }
                    mSurfaceContainer.addView(
                        jzvdStd, FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    )
                    return true
                }
            })
    }

    private fun addVideoViewFromList() {
        mSurfaceContainer.viewTreeObserver
            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    mSurfaceContainer.viewTreeObserver.removeOnPreDrawListener(this)
                    //将Jzvd从列表中移除再添加到播放详情渲染控件中，实现无缝续播功能
                    val parent: ViewParent = JzvdStdRv.CURRENT_JZVD.parent
                    (parent as ViewGroup).removeView(JzvdStdRv.CURRENT_JZVD)
                    mSurfaceContainer.addView(
                        JzvdStdRv.CURRENT_JZVD, FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    )
                    //获取视频详情页面视频渲染控件的坐标以及宽高
                    mCurrentAttr = ViewAttr()
                    val location = IntArray(2)
                    mSurfaceContainer.getLocationInWindow(location)
                    mCurrentAttr.x = location[0]
                    mCurrentAttr.y = location[1] - getStatusBarHeight()
                    mCurrentAttr.width = mSurfaceContainer.measuredWidth
                    mCurrentAttr.height = mSurfaceContainer.measuredHeight
                    //开启平移动画实现将列表播放控件平移到详情渲染控件的过渡效果
                    ViewMoveHelper(mSurfaceContainer, viewAttr, mCurrentAttr, DURATION).startAnim()
                        .addListener(object :
                            AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                getRelateVideoList()
                            }
                        })

                    return true
                }
            })
    }

    private fun initTransition() {
        //因为进入视频详情页面后还需请求数据，所以在过渡动画完成后在请求数据
        //延迟动画执行
        postponeEnterTransition()
        //设置共用元素对应的View
        ViewCompat.setTransitionName(mSurfaceContainer, BaseConstant.SHARED_ELEMENT_NAME)
        //获取共享元素进入转场对象
        mTransition = window.sharedElementEnterTransition
        //设置共享元素动画执行完成的回调事件
        mTransition?.addListener(object : TransitionListenerAdapter() {
            override fun onTransitionEnd(transition: Transition?) {
                getRelateVideoList()
                //移除共享元素动画监听事件
                mTransition?.removeListener(this)
            }
        })
        //开始动画执行
        startPostponedEnterTransition()
    }

    private fun getRelateVideoList() {
        mSwipeRefreshLayout.isVisible = true
        mViewModel.getRelateVideoList(videoModel.id).observerKt {
            mAdapter.addData(it)
        }
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        if (isInitViewAttr()) {
            backAnimation()
        } else {
            super.onBackPressed()
        }
    }

    private fun isInitViewAttr() = ::viewAttr.isInitialized

    private fun backAnimation() {
        ViewMoveHelper(mSurfaceContainer, mCurrentAttr, viewAttr, DURATION).startAnim()
        mVideoBackground.isVisible = false
        mSwipeRefreshLayout.isVisible = false
        mSurfaceContainer.postDelayed({
            LiveDataBus.with<VideoAutoPlayEvent>(BaseConstant.VIDEO_AUTO_PLAY_EVENT)
                .setData(VideoAutoPlayEvent())
            finish()
            overridePendingTransition(0, 0)
        }, DURATION)
    }
}
