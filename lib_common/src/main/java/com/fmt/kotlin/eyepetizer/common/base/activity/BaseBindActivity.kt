package com.fmt.kotlin.eyepetizer.common.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.ErrorState
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.LoadState
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.SuccessState
import com.fmt.kotlin.eyepetizer.common.ext.errorToast
import java.lang.reflect.ParameterizedType

abstract class BaseBindActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    abstract val getLayoutRes: Int
    lateinit var mBind: DB
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initWindow()
        super.onCreate(savedInstanceState)
        mBind = DataBindingUtil.setContentView(
            this,
            getLayoutRes
        )
        initView()
        initViewModel()
        initData()
    }

    private fun initViewModel() {
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        mViewModel = ViewModelProvider(this)[parameterizedType.actualTypeArguments[1] as Class<VM>]
        mViewModel.mStateLiveData.observe(this, { state ->
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
        })
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

    override fun onDestroy() {
        super.onDestroy()
        if (::mBind.isInitialized) {
            mBind.unbind()
        }
    }

}