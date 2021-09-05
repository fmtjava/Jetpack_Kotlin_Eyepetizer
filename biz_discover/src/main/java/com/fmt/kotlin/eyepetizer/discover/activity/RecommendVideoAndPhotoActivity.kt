package com.fmt.kotlin.eyepetizer.discover.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.jzvd.Jzvd
import com.fmt.kotlin.eyepetizer.common.ext.fromJson
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.common.ext.loadUrl
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.databinding.DiscoverActivityRecommendVideoAndPhotoBinding
import com.fmt.kotlin.eyepetizer.discover.model.Item
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.android.synthetic.main.discover_activity_recommend_video_and_photo.*

class RecommendVideoAndPhotoActivity : AppCompatActivity() {

    lateinit var mModel: Item

    companion object {
        const val RECOMMEND_DATA_KEY = "recommend_data_key"
        fun start(content: Activity, json: String) {
            with(Intent(content, RecommendVideoAndPhotoActivity::class.java)) {
                putExtra(RECOMMEND_DATA_KEY, json)
                content.startActivity(this)
                content.overridePendingTransition(
                    R.anim.discover_slide_bottom_in,
                    0
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionStatusBar()
        val binding = DataBindingUtil.setContentView<DiscoverActivityRecommendVideoAndPhotoBinding>(
            this,
            R.layout.discover_activity_recommend_video_and_photo
        )
        mModel =
            fromJson(intent.getStringExtra(RECOMMEND_DATA_KEY).toString())
        binding.model = mModel
        if (isVideoType()) {
            jzvdStd.setUp(mModel.data.content.data.playUrl, mModel.data.content.data.title)
            jzvdStd.startVideo()
        } else {
            initPhoto()
        }
        fl_top_back.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initPhoto() {
        mMaterialButton.text = "1/${mModel.data.content.data.urls.size}"
        mViewPager.adapter = PhotoPagerAdapter()
        mViewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mMaterialButton.text = "${position + 1}/${mModel.data.content.data.urls.size}"
            }
        })
    }

    override fun onBackPressed() {
        if (isVideoType()) {
            if (Jzvd.backPress()) {
                return
            }
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        if (isVideoType()) {
            Jzvd.releaseAllVideos()
        }
    }

    inner class PhotoPagerAdapter : PagerAdapter() {
        override fun getCount(): Int = mModel.data.content.data.urls.size

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            val photoView = PhotoView(container.context)
            photoView.loadUrl(mModel.data.content.data.urls[position], true)
            container.addView(
                photoView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            return photoView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`
    }

    private fun isVideoType() = mModel.data.content.type == "video"

    override fun finish() {
        super.finish()
        overridePendingTransition(
            0,
            R.anim.discover_slide_bottom_out
        )
    }
}