package com.fmt.kotlin.eyepetizer.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.fmt.kotlin.eyepetizer.person.databinding.PersonFragmentBinding
import com.fmt.kotlin.eyepetizer.provider.router.RouterPath

@Route(path = RouterPath.Person.PATH_PERSON_HOME)
class PersonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PersonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}