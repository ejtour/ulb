package com.ulb.code.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ulb.code.R
import com.ulb.common.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import java.util.*

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.fragment_main, container, false)

        return super.onCreateView(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userRes.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            txt_name.text = it.userName
        })

        txt_name.setOnClickListener {
            loadDataOnce()
        }

    }

    override fun loadDataOnce() {
        //lifecycleScope和viewModelScope会绑定调用者的生命周期，因此通常情况下不需要手动去停止
        viewModel.getUserInfo()
    }

}


