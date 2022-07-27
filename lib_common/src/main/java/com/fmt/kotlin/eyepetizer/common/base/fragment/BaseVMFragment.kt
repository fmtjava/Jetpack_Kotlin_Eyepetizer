package com.fmt.kotlin.eyepetizer.common.base.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.ErrorState
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.LoadState
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.SuccessState
import com.fmt.kotlin.eyepetizer.common.ext.errorToast
import java.lang.reflect.ParameterizedType

abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {

    lateinit var mViewModel: VM

    override fun initData() {
        initViewModel()
        lazyLoadData()
    }

    private fun initViewModel() {
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        mViewModel = ViewModelProvider(this)[parameterizedType.actualTypeArguments[0] as Class<VM>]
        mViewModel.mStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                LoadState -> {
                    showLoading()
                }
                SuccessState -> {
                    hideLoading()
                }
                is ErrorState -> {
                    hideLoading()
                    state.errorMsg?.let { errorToast(it) }
                    handlerError()
                }
            }
        }
    }

    protected fun <T : Any> LiveData<T>.observerKt(block: (T) -> Unit) {
        this.observe(viewLifecycleOwner) {
            block(it)
        }
    }

    //由于每个页面的加载框可能不一致，所以此处暴露给子类实现
    open fun showLoading() {

    }

    open fun hideLoading() {

    }

    open fun handlerError() {

    }

    abstract fun lazyLoadData()
}