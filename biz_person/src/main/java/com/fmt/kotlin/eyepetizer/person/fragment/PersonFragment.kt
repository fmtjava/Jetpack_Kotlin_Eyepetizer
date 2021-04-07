package com.fmt.kotlin.eyepetizer.person.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.fmt.kotlin.eyepetizer.common.base.fragment.BaseFragment
import com.fmt.kotlin.eyepetizer.common.ext.startActivity
import com.fmt.kotlin.eyepetizer.person.R
import com.fmt.kotlin.eyepetizer.person.activity.VideoWatchRecordActivity
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import kotlinx.android.synthetic.main.person_fragment.*

@Route(path = RouterPath.Person.PATH_PERSON_HOME)
class PersonFragment : BaseFragment() {
    override val getLayoutRes: Int
        get() = R.layout.person_fragment

    override fun initView() {
        tv_watch_history.setOnClickListener {
            context?.let { mActivity.startActivity<VideoWatchRecordActivity>() }
        }
    }

    override fun initData() {

    }
}