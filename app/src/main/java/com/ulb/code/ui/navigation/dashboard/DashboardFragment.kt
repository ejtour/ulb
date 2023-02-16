package com.ulb.code.ui.navigation.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulb.code.R
import com.ulb.common.base.BaseFragment

class DashboardFragment : BaseFragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        return super.onCreateView(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun loadDataOnce() {
        //lifecycleScope和viewModelScope会绑定调用者的生命周期，因此通常情况下不需要手动去停止

    }

}


