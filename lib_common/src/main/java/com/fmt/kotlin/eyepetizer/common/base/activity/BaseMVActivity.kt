package com.fmt.kotlin.eyepetizer.common.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.ErrorState
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.LoadState
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.SuccessState
import com.fmt.kotlin.eyepetizer.common.ext.errorToast
import java.lang.reflect.ParameterizedType

abstract class BaseMVActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract val getLayoutRes: Int
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initWindow()
        super.onCreate(savedInstanceState)
        setLayout()
        initView()
        initViewModel()
        initData()
        initEvent()
    }

    open fun setLayout() {
        setContentView(getLayoutRes)
    }

    private fun initViewModel() {
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        mViewModel = ViewModelProvider(this)[parameterizedType.actualTypeArguments[0] as Class<VM>]
        mViewModel.mStateLiveData.observe(this) { state ->
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

    //扩展liveData的observe函数
    protected fun <T : Any> LiveData<T>.observerKt(block: (T) -> Unit) {
        this.observe(this@BaseMVActivity) {
            block(it)
        }
    }

    open fun showLoading() {

    }

    open fun hideLoading() {

    }

    open fun handlerError() {

    }

    open fun initWindow() {

    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initEvent()

}