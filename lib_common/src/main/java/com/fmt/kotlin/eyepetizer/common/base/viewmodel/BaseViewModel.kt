package com.fmt.kotlin.eyepetizer.common.base.viewmodel

import androidx.lifecycle.*
import java.lang.Exception

open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<State>()

    fun <T> liveDataEx(block: suspend () -> T) = liveData {
        try {
            mStateLiveData.value = LoadState
            emit(block())
            mStateLiveData.value = SuccessState
        } catch (e: Exception) {
            mStateLiveData.value = ErrorState(e.message)
        }
    }
}

sealed class State

object LoadState : State()

object SuccessState : State()

class ErrorState(val errorMsg: String?) : State()
