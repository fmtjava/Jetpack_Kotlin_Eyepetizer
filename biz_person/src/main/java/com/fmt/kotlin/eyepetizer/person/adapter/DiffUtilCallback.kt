package com.fmt.kotlin.eyepetizer.person.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fmt.kotlin.eyepetizer.provider.model.Data

/**
 * 差分数据对比实现类
 */
class DiffUtilCallback(private val oldList: List<Data>, private val newList: List<Data>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id
}