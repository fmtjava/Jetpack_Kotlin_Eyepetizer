package com.fmt.kotlin.eyepetizer.dialy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmt.kotlin.eyepetizer.dialy.databinding.DailyItemBannerImageBinding
import com.fmt.kotlin.eyepetizer.dialy.model.DailyItem
import com.youth.banner.adapter.BannerAdapter

class BannerImageAdapter(context: Context, datas: List<DailyItem>) :
    BannerAdapter<DailyItem, BannerImageAdapter.BannerViewHolder>(datas) {

    private val mInflater = LayoutInflater.from(context)

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val binding = DailyItemBannerImageBinding.inflate(mInflater, parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindView(holder: BannerViewHolder, data: DailyItem, position: Int, size: Int) {
        holder.binding.model = data.data.content
    }

    class BannerViewHolder(val binding: DailyItemBannerImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}