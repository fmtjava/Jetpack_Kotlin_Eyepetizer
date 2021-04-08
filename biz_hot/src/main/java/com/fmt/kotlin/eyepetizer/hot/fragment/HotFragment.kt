package com.fmt.kotlin.eyepetizer.hot.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseVMFragment
import com.fmt.kotlin.eyepetizer.hot.R
import com.fmt.kotlin.eyepetizer.hot.model.TabInfo
import com.fmt.kotlin.eyepetizer.hot.viewmodel.HotViewModel
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.hot_fragment.*

@AndroidEntryPoint
@Route(path = RouterPath.Hot.PATH_HOT_HOME)
class HotFragment : BaseVMFragment<HotViewModel>() {

    override val getLayoutRes: Int
        get() = R.layout.hot_fragment

    override fun initView() {

    }

    override fun lazyLoadData() {
        mViewModel.getHotTabList().observe(viewLifecycleOwner, {
            initTabInfo(it)
        })
    }

    private fun initTabInfo(tabInfo: TabInfo) {
        val fragmentList = mutableListOf<Fragment>()
        tabInfo.tabInfo.tabList.forEach { tab ->
            mTabLayout.addTab(mTabLayout.newTab())
            fragmentList.add(RankListFragment.newInstance(tab.apiUrl))
        }
        mViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragmentList.size

            override fun createFragment(position: Int): Fragment = fragmentList[position]
        }
        TabLayoutMediator(
            mTabLayout, mViewPager
        ) { tab, position ->
            tab.text = tabInfo.tabInfo.tabList[position].name
        }.attach()
    }

}