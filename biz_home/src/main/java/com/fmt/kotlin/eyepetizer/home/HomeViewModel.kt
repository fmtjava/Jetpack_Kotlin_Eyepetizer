package com.fmt.kotlin.eyepetizer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) : BaseViewModel() {

    private val HOME_PAGE_INDEX = "home_page_index"
    private val mLiveData = MutableLiveData<Int>()

    fun getSelected(): LiveData<Int> {
        //从缓存中读取，防止Activity因内存不知等原因被回收重建后，Fragment重叠问题
        if (mLiveData.value == null) {
            val index = savedStateHandle.get<Int>(HOME_PAGE_INDEX) ?: 0
            mLiveData.postValue(index)
        }
        return mLiveData
    }

    //保存每一次的下标选中
    fun saveSelect(selectIndex: Int) {
        savedStateHandle.set(HOME_PAGE_INDEX, selectIndex)
    }

}