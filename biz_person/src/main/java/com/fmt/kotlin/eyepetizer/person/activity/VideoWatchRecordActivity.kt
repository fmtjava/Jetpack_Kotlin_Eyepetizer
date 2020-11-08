package com.fmt.kotlin.eyepetizer.person.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmt.kotlin.eyepetizer.common.ext.dp2px
import com.fmt.kotlin.eyepetizer.person.R
import com.fmt.kotlin.eyepetizer.person.adapter.VideoWatchRecordAdapter
import com.fmt.kotlin.eyepetizer.provider.service.warp.VideoWatchWarp
import com.gyf.immersionbar.ktx.immersionBar
import com.yanzhenjie.recyclerview.OnItemMenuClickListener
import com.yanzhenjie.recyclerview.SwipeMenuCreator
import com.yanzhenjie.recyclerview.SwipeMenuItem
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import kotlinx.android.synthetic.main.person_activity_video_watch_record.*

class VideoWatchRecordActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        VideoWatchRecordAdapter(
            this,
            VideoWatchWarp.getVideoWatchList()
        )
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, VideoWatchRecordActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(android.R.color.white)
            statusBarDarkFont(true, 0.2f)
        }
        setContentView(R.layout.person_activity_video_watch_record)
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        mToolbar.setNavigationOnClickListener { onBackPressed() }

        initSwipeMenu()
    }

    private fun initSwipeMenu() {
        val mSwipeMenuCreator =
            SwipeMenuCreator { _, rightMenu, _ ->
                val deleteItem: SwipeMenuItem =
                    SwipeMenuItem(this).setBackground(R.drawable.person_selector_red)
                        .setImage(R.drawable.person_ic_action_delete)
                        .setWidth(dp2px(70f))
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                rightMenu.addMenuItem(deleteItem)
            }

        val mItemMenuClickListener =
            OnItemMenuClickListener { menuBridge, position ->
                menuBridge.closeMenu()

                if (menuBridge.direction == SwipeRecyclerView.RIGHT_DIRECTION) {
                    VideoWatchWarp.removeVideoWatchRecord(mAdapter.mDataList[position])
                    mAdapter.remove(position)
                }
            }

        mSwipeRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator)
        mSwipeRecyclerView.setOnItemMenuClickListener(mItemMenuClickListener)

        mSwipeRecyclerView.isSwipeItemMenuEnabled = true
        mSwipeRecyclerView.layoutManager = LinearLayoutManager(this)
        mSwipeRecyclerView.adapter = mAdapter
    }
}