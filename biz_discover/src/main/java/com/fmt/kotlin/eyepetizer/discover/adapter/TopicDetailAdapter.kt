package com.fmt.kotlin.eyepetizer.discover.adapter

import android.app.Activity
import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fmt.kotlin.eyepetizer.common.ext.dp2px
import com.fmt.kotlin.eyepetizer.common.util.ShareUtils
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemTopicDetailBinding
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverItemTopicTagBinding
import com.fmt.kotlin.eyepetizer.discover.model.Item
import com.fmt.kotlin.eyepetizer.discover.utils.AutoPlayUtils
import com.fmt.kotlin.eyepetizer.provider.jzvd.JzvdStdRv
import com.fmt.kotlin.eyepetizer.provider.jzvd.ViewAttr
import com.fmt.kotlin.eyepetizer.provider.model.Tag

class TopicDetailAdapter(private val mActivity: Activity, val mVideoClick: OnVideoClick) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.discover_item_topic_detail), LoadMoreModule {

    init {
        addChildClickViewIds(R.id.tv_share)
        setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.tv_share) {
                val itemData = data[position].data
                ShareUtils.shareText(mActivity, itemData.content.data.playUrl)
            }
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<DiscoverItemTopicDetailBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: Item) {
        val binding =
            DataBindingUtil.getBinding<DiscoverItemTopicDetailBinding>(holder.itemView)
        binding?.model = item
        binding?.activity = mActivity

        val container = binding!!.surfaceContainer
        dealJzvdStdRv(container, item)
    }

    private fun dealJzvdStdRv(container: FrameLayout, item: Item) {
        container.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, dp2px(4f).toFloat())
            }
        }
        container.clipToOutline = true
        val jzvdStdRv: JzvdStdRv
        //从播放详情页面回到列表后，需要先从详情渲染控件中移除再添加到Item布局中
        if (JzvdStdRv.CURRENT_JZVD != null && AutoPlayUtils.positionInList == data.indexOf(item)) {
            val parent = JzvdStdRv.CURRENT_JZVD.parent
            if (parent != null) {
                (parent as ViewGroup).removeView(JzvdStdRv.CURRENT_JZVD)
            }
            container.removeAllViews()
            container.addView(
                JzvdStdRv.CURRENT_JZVD, FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            jzvdStdRv = JzvdStdRv.CURRENT_JZVD as JzvdStdRv
        } else {
            //当Item中未添加JzvdStdRv时，此时需要调用addView进行添加
            if (container.childCount == 0) {
                jzvdStdRv = JzvdStdRv(container.context)
                container.addView(
                    jzvdStdRv,
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )
            } else {//当已经添加过了，则直接取出第一个子控件即可，达到复用的效果
                jzvdStdRv = container.getChildAt(0) as JzvdStdRv
            }
            //设置视频播放资源以及封面图
            jzvdStdRv.setUp(item.data.content.data.playUrl, "")
            jzvdStdRv.posterImageView.load(item.data.content.data.cover!!.feed)
        }
        jzvdStdRv.id = R.id.jzvdplayer
        jzvdStdRv.isAtList = true
        jzvdStdRv.setClickUi(object : JzvdStdRv.ClickUi {
            override fun onClickUiToggle() {
                //点击视频播放/暂停时保存当前的播放位置
                AutoPlayUtils.positionInList = data.indexOf(item)
                //修改当前播放器是否列表播放的标记
                jzvdStdRv.isAtList = false
                //记录当前播放控件的坐标以及宽高
                val attr = ViewAttr()
                val location = IntArray(2)
                jzvdStdRv.getLocationInWindow(location)
                attr.x = location[0]
                attr.y = location[1]
                attr.width = jzvdStdRv.measuredWidth
                attr.height = jzvdStdRv.measuredHeight
                mVideoClick.videoClick(
                    jzvdStdRv, attr, data.indexOf(
                        item
                    )
                )
                jzvdStdRv.setClickUi(null)
            }

            override fun onClickStart() {
                //视频开始播放时记录当前的播放位置
                AutoPlayUtils.positionInList = data.indexOf(item)
            }
        })
    }

    interface OnVideoClick {
        fun videoClick(focusView: ViewGroup, viewAttr: ViewAttr, position: Int)
    }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["tagList", "activity"])
        fun LinearLayout.setTagData(tagList: List<Tag>, activity: Activity) {
            removeAllViews()
            var tags = tagList
            if (tags.size > 3) {
                tags = tags.subList(0, 3)
            }
            tags.forEach { tag ->
                val binding =
                    DiscoverItemTopicTagBinding.inflate(activity.layoutInflater, this, false)
                binding.name = tag.name
                addView(binding.root)
            }
        }
    }
}



