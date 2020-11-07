package com.fmt.kotlin.eyepetizer.player.activity

import android.transition.Transition
import android.transition.TransitionListenerAdapter
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jzvd.Jzvd
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.fmt.kotlin.eyepetizer.common.base.activity.BaseBindActivity
import com.fmt.kotlin.eyepetizer.player.R
import com.fmt.kotlin.eyepetizer.player.adapter.VideoRelateAdapter
import com.fmt.kotlin.eyepetizer.player.databinding.PlayerActivityVideoBinding
import com.fmt.kotlin.eyepetizer.player.viewmodel.VideoPlayerViewModel
import com.fmt.kotlin.eyepetizer.provider.constant.BaseConstant
import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.player_activity_video.*

@Route(path = RouterPath.Video.PATH_VIDEO_HOME)
class VideoPlayerActivity : BaseBindActivity<PlayerActivityVideoBinding, VideoPlayerViewModel>() {

    private var mTransition: Transition? = null

    @Autowired(name = BaseConstant.VIDEO_MODE_KEY)
    lateinit var videoModel: Data

    @JvmField
    @Autowired(name = BaseConstant.VIDEO_IS_FROM_RELATE_KEY)
    var fromRelate: Boolean = false

    private val mAdapter: VideoRelateAdapter by lazy { VideoRelateAdapter(this) }

    override val getLayoutRes: Int
        get() = R.layout.player_activity_video

    override fun initView() {
        immersionBar()
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
        mBind.videoModel = videoModel

        if (fromRelate) {
            getRelateVideoList()
        } else {
            initTransition()
        }
    }

    private fun initTransition() {
        //因为进入视频详情页面后还需请求数据，所以在过渡动画完成后在请求数据
        //延迟动画执行
        postponeEnterTransition()
        ViewCompat.setTransitionName(mVideoView, BaseConstant.SHARED_ELEMENT_NAME)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mTransition = window.sharedElementEnterTransition
            mTransition?.addListener(object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition?) {
                    getRelateVideoList()
                    mTransition?.removeListener(this)
                }
            })
        }
        //开始动画执行
        startPostponedEnterTransition()
    }

    private fun getRelateVideoList() {
        mViewModel.getRelateVideoList(videoModel.id).observe(this, {
            mAdapter.addData(it)
        })
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }
}
