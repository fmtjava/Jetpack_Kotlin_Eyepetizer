package com.fmt.kotlin.eyepetizer.common.base.viewmodel

import androidx.lifecycle.*
import java.lang.Exception

open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<State>()

    fun <T> liveDataEx(block: suspend LiveDataScope<T>.() -> T) = liveData<T> {
        try {
            mStateLiveData.postValue(LoadState)
            emit(block())
            mStateLiveData.postValue(SuccessState)
        } catch (e: Exception) {
            mStateLiveData.postValue(ErrorState(e.message))
        }
    }
}

sealed class State

object LoadState : State()

object SuccessState : State()

class ErrorState(val errorMsg: String?) : State()
