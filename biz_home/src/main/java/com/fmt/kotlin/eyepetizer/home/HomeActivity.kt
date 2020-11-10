package com.fmt.kotlin.eyepetizer.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        immersionStatusBar(true,android.R.color.white,true,0.2f)
        initViewPager()
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        //去掉底部默认选中背景
        mBottomNavigationView.itemIconTintList = null
        mBottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_daily -> mViewPager.currentItem = 0
                R.id.item_discover -> mViewPager.currentItem = 1
                R.id.item_hot -> mViewPager.currentItem = 2
                R.id.item_person -> mViewPager.currentItem = 3
            }
            true
        }
    }

    private fun initViewPager() {
        val dailyFragment =
            ARouter.getInstance().build(RouterPath.Daily.PATH_DAILY_HOME).navigation() as Fragment
        val discoverFragment = ARouter.getInstance().build(RouterPath.Discover.PATH_DISCOVER_HOME)
            .navigation() as Fragment
        val hotFragment =
            ARouter.getInstance().build(RouterPath.Hot.PATH_HOT_HOME).navigation() as Fragment
        val personFragment =
            ARouter.getInstance().build(RouterPath.Person.PATH_PERSON_HOME).navigation() as Fragment

        val fragmentList = mutableListOf<Fragment>().apply {
            add(dailyFragment)
            add(discoverFragment)
            add(hotFragment)
            add(personFragment)
        }
        mViewPager.isUserInputEnabled = false
        mViewPager.adapter = HomePageAdapter(fragmentList, this)
        mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBottomNavigationView.selectedItemId =
                    mBottomNavigationView.menu.getItem(position).itemId
            }
        })
    }

    class HomePageAdapter(
        private val fragmentList: List<Fragment>,
        fragmentActivity: FragmentActivity
    ) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }
}