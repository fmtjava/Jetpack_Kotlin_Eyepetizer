package com.fmt.kotlin.eyepetizer

import androidx.lifecycle.lifecycleScope
import com.fmt.kotlin.eyepetizer.common.base.activity.BaseBindActivity
import com.fmt.kotlin.eyepetizer.common.ext.startActivity
import com.fmt.kotlin.eyepetizer.databinding.ActivitySplashBinding
import com.fmt.kotlin.eyepetizer.home.HomeActivity
import kotlinx.coroutines.delay

class SplashActivity : BaseBindActivity<ActivitySplashBinding>() {

    override val getLayoutRes: Int
        get() = R.layout.activity_splash

    override fun initData() {
        mBind.context = this
        lifecycleScope.launchWhenCreated {
            delay(500)
            startActivity<HomeActivity>()
            finish()
        }
    }
}