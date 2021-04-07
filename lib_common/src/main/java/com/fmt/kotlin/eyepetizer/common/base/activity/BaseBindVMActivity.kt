package com.fmt.kotlin.eyepetizer.common.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.fmt.kotlin.eyepetizer.common.base.viewmodel.BaseViewModel

abstract class BaseBindVMActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseMVActivity<VM>() {

    lateinit var mBind: DB

    override fun setLayout() {
        mBind = DataBindingUtil.setContentView(
            this,
            getLayoutRes
        )
        mBind.lifecycleOwner = this
    }

    override fun initEvent() {
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBind.isInitialized) {
            mBind.unbind()
        }
    }
}