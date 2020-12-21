package com.fmt.kotlin.eyepetizer.home

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.common.ext.infoToast
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    private var mExitTime: Long = 0

    private var mDailyFragment: Fragment? = null
    private var mDiscoveryFragment: Fragment? = null
    private var mHotFragment: Fragment? = null
    private var mMineFragment: Fragment? = null

    private lateinit var mHomeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        window.sharedElementsUseOverlay = false
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        immersionStatusBar(true, android.R.color.white, true, 0.2f)
        initBottomNavigation()
        initViewModel()
    }

    private fun initViewModel() {
        mHomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mHomeViewModel.getSelected().observe(this, { index ->
            switchFragment(index)
        })
    }

    private fun initBottomNavigation() {
        //去掉底部默认选中背景
        mBottomNavigationView.itemIconTintList = null
        mBottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_daily -> saveAndSwitch(0)
                R.id.item_discover -> saveAndSwitch(1)
                R.id.item_hot -> saveAndSwitch(2)
                R.id.item_person -> saveAndSwitch(3)
            }
            true
        }
    }

    private fun saveAndSwitch(index: Int) {
        mHomeViewModel.saveSelect(index)
        switchFragment(index)
    }

    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 -> mDailyFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Daily.PATH_DAILY_HOME)
                .navigation() as Fragment).let {
                mDailyFragment = it
                transaction.add(R.id.mContentFL, it, RouterPath.Daily.PATH_DAILY_HOME)
            }
            1 -> mDiscoveryFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Discover.PATH_DISCOVER_HOME)
                .navigation() as Fragment).let {
                mDiscoveryFragment = it
                transaction.add(R.id.mContentFL, it, RouterPath.Discover.PATH_DISCOVER_HOME)
            }
            2 -> mHotFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Hot.PATH_HOT_HOME)
                .navigation() as Fragment).let {
                mHotFragment = it
                transaction.add(R.id.mContentFL, it, RouterPath.Hot.PATH_HOT_HOME)
            }
            3 -> mMineFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Person.PATH_PERSON_HOME)
                .navigation() as Fragment).let {
                mMineFragment = it
                transaction.add(R.id.mContentFL, it, RouterPath.Person.PATH_PERSON_HOME)
            }
        }
        transaction.commitNowAllowingStateLoss()
    }

    //隐藏所有的Fragment
    private fun hideFragments(transaction: FragmentTransaction) {
        mDailyFragment?.let { transaction.hide(it) }
        mDiscoveryFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                infoToast(getString(R.string.home_exit_tips))
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}