package com.fmt.kotlin.eyepetizer.discover.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseFragment
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import kotlinx.android.synthetic.main.discover_fragment.*

@Route(path = RouterPath.Discover.PATH_DISCOVER_HOME)
class DiscoverFragment : BaseFragment() {

    private val mTabTitles: Array<String> by lazy { resources.getStringArray(R.array.discover_titles) }
    override val getLayoutRes: Int
        get() = R.layout.discover_fragment

    override fun initView() {
        //如果ViewPage2这里需要处理两种冲突：
        //1.横向RecyclerView与ViewPage2的滑动冲突
        //2.垂直方向RecyclerView与横向RecyclerView的滑动冲突
        //比较麻烦，所以此处采用了ViewPager
        mViewPager.adapter = object :
            FragmentStatePagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getCount(): Int = 2

            override fun getItem(position: Int): Fragment {
                return if (position == 0) {
                    FollowFragment()
                } else {
                    CategoryFragment()
                }
            }

            override fun getPageTitle(position: Int): CharSequence? = mTabTitles[position]
        }
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun initData() {
    }
}