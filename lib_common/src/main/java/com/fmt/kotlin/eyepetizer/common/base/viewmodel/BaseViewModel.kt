package com.fmt.kotlin.eyepetizer.common.base.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<State>()

    fun <T> liveDataEx(block: suspend () -> T) = liveData {
        kotlin.runCatching {
            mStateLiveData.value = LoadState
            block()
        }.onSuccess {
            emit(it)
            mStateLiveData.value = SuccessState
        }.onFailure { e ->
            mStateLiveData.value = ErrorState(e.message)
        }
    }

    //使用Flow流式编程类似RxJava
    fun <T> flowEx(block: suspend () -> T) = flow {
        emit(block())
    }.onStart {
        mStateLiveData.value = LoadState
    }.onCompletion {
        mStateLiveData.value = SuccessState
    }.catch { cause ->
        mStateLiveData.value = ErrorState(cause.message)
    }.asLiveData()
}

sealed class State

object LoadState : State()

object SuccessState : State()

class ErrorState(val errorMsg: String?) : State()
