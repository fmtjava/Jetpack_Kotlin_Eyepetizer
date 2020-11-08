package com.fmt.kotlin.eyepetizer.person.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.fmt.kotlin.eyepetizer.person.activity.VideoWatchRecordActivity
import com.fmt.kotlin.eyepetizer.person.databinding.PersonFragmentBinding
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath
import kotlinx.android.synthetic.main.person_fragment.*

@Route(path = RouterPath.Person.PATH_PERSON_HOME)
class PersonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return PersonFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_watch_history.setOnClickListener {
            context?.let { c -> VideoWatchRecordActivity.start(c) }
        }
    }
}