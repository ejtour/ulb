package com.ulb.code.ui.navigation.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.ulb.code.R
import com.ulb.common.base.BaseFragment
import com.ulb.common.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        return super.onCreateView(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_sort_goods.setOnClickListener {

            LogUtils.d("-->", "action_navigation_to_dashboard")

            findNavController().navigate(R.id.action_navigation_to_dashboard)
        }
    }

    override fun loadDataOnce() {
        //lifecycleScope和viewModelScope会绑定调用者的生命周期，因此通常情况下不需要手动去停止
//        viewModel.getUserInfo()
    }

}


