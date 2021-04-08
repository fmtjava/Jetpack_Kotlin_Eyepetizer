package com.fmt.kotlin.eyepetizer.discover.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
import cn.jzvd.Jzvd
import com.fmt.kotlin.eyepetizer.common.base.activity.BaseBindVMActivity
import com.fmt.kotlin.eyepetizer.common.ext.commonMaterialContainerTransformConfig
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.common.ext.setToolBar
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.adapter.TopicDetailAdapter
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverActivityTopicDetailBinding
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemTopicDetailHeaderBinding
import com.fmt.kotlin.eyepetizer.discover.model.TopicDetailModel
import com.fmt.kotlin.eyepetizer.discover.utils.AutoPlayUtils
import com.fmt.kotlin.eyepetizer.discover.viewmodel.TopicViewModel
import com.fmt.kotlin.eyepetizer.provider.constant.BaseConstant
import com.fmt.kotlin.eyepetizer.provider.jzvd.ViewAttr
import com.fmt.kotlin.eyepetizer.provider.model.event.VideoAutoPlayEvent
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity
import com.fmt.livedatabus.LiveDataBus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.discover_activity_topic_detail.*

@AndroidEntryPoint
class TopicDetailActivity :
    BaseBindVMActivity<TopicViewModel, DiscoverActivityTopicDetailBinding>(),
    TopicDetailAdapter.OnVideoClick {

    private var mId: Int = 0

    private val mAdapter by lazy { TopicDetailAdapter(this, this) }

    private var mHeaderBinding: DiscoverItemTopicDetailHeaderBinding? = null

    companion object {
        const val ID = "id"
        fun start(context: Activity, id: Int, startView: View) {
            val intent = Intent(context, TopicDetailActivity::class.java).apply {
                putExtra(ID, id)
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                context,
                startView,
                context.getString(R.string.shared_element_container)
            )
            context.startActivity(intent, options.toBundle())
        }
    }

    override val getLayoutRes: Int
        get() = R.layout.discover_activity_topic_detail

    override fun initWindow() {
        commonMaterialContainerTransformConfig()
    }

    override fun initView() {
        immersionStatusBar(true, android.R.color.white, true, 0.2f)
        setToolBar(mToolbar)
        mSwipeRefreshLayout.setOnRefreshListener {
            getTopicDetail()
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addOnChildAttachStateChangeListener(object :
            OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {}
            override fun onChildViewDetachedFromWindow(view: View) {
                //当Item视图不可见时，暂停视频播放
                val jzvd = view.findViewById<Jzvd>(R.id.jzvdplayer)
                if (jzvd != null && Jzvd.CURRENT_JZVD != null && jzvd.jzDataSource.containsTheUrl(
                        Jzvd.CURRENT_JZVD.jzDataSource.currentUrl
                    )
                ) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.releaseAllVideos()
                    }
                }
            }
        })

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //当列表停止滚动后，获取第一个可见Item以及最后一个可见Item的下标
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    AutoPlayUtils.onScrollPlayVideo(
                        recyclerView,
                        R.id.cl_topic,
                        R.id.jzvdplayer,
                        layoutManager.findFirstVisibleItemPosition(),
                        layoutManager.findLastVisibleItemPosition()
                    )
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy != 0) {
                    AutoPlayUtils.onScrollReleaseAllVideos(
                        layoutManager.findFirstVisibleItemPosition(),
                        layoutManager.findLastVisibleItemPosition(),
                        0.2f
                    )
                }
            }
        })
    }

    override fun showLoading() {
        mSwipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun initData() {
        mId = intent.getIntExtra(ID, 0)
        getTopicDetail()
    }

    private fun getTopicDetail() {
        mViewModel.getTopicDetail(mId).observerKt { topicDetailModel ->
            initHeaderView(topicDetailModel)
            mAdapter.setList(topicDetailModel.itemList)
        }
    }

    override fun initEvent() {
        LiveDataBus.with<VideoAutoPlayEvent>(BaseConstant.VIDEO_AUTO_PLAY_EVENT).observerKt {
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun initHeaderView(topicDetailModel: TopicDetailModel?) {
        topicDetailModel?.let { model ->
            mBind.title = model.brief
            if (mHeaderBinding == null) {
                mHeaderBinding =
                    DiscoverItemTopicDetailHeaderBinding.inflate(LayoutInflater.from(this))
                mHeaderBinding!!.model = model
                mAdapter.addHeaderView(mHeaderBinding!!.root)
            } else {
                mHeaderBinding?.model = model
            }
        }
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        AutoPlayUtils.positionInList = -1
        Jzvd.releaseAllVideos()
    }

    override fun videoClick(focusView: ViewGroup, viewAttr: ViewAttr, position: Int) {
        go2VideoPlayerActivity(
            this,
            mAdapter.data[position].data.content.data,
            viewAttr
        )
    }
}