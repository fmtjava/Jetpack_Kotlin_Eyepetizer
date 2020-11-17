package com.fmt.kotlin.eyepetizer.person.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.fmt.kotlin.eyepetizer.person.R
import com.fmt.kotlin.eyepetizer.provider.constant.BaseConstant
import com.fmt.kotlin.eyepetizer.provider.databinding.ItemRelateVideoBinding
import com.fmt.kotlin.eyepetizer.provider.model.Data
import com.fmt.kotlin.eyepetizer.provider.router.go2VideoPlayerActivity

class VideoWatchRecordAdapter(
    private val mActivity: Activity,
    val mDataList: MutableList<Data> = mutableListOf()
) :
    RecyclerView.Adapter<VideoWatchRecordAdapter.VideoWatchRecordViewHolder>() {

    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mActivity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoWatchRecordViewHolder {
        return VideoWatchRecordViewHolder(
            ItemRelateVideoBinding.inflate(
                mLayoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoWatchRecordViewHolder, position: Int) {
        holder.setData(mDataList[position])
    }

    override fun getItemCount(): Int = mDataList.size

    fun remove(position: Int) {
        mDataList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun newData(dataList: List<Data>) {
        mDataList.clear()
        mDataList.addAll(dataList)
    }

    inner class VideoWatchRecordViewHolder(private val binding: ItemRelateVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(data: Data) {
            binding.tvTitle.setTextColor(
                ContextCompat.getColor(mActivity, R.color.color_black_87)
            )
            binding.tvCategory.setTextColor(
                ContextCompat.getColor(mActivity, R.color.color_black_26)
            )
            ViewCompat.setTransitionName(
                binding.ivCover,
                BaseConstant.SHARED_ELEMENT_NAME
            )
            binding.model = data
            binding.root.setOnClickListener {
                go2VideoPlayerActivity(
                    mActivity,
                    binding.ivCover,
                    data
                )
            }
        }
    }
}
