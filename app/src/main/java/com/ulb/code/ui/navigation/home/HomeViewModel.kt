package com.ulb.code.ui.navigation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ulb.common.request
import com.ulb.code.res.UserRes
import com.ulb.code.ui.navigation.home.net.HomeReposition
import com.ulb.common.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val homeReposition = HomeReposition()

    var nameLiveData = MutableLiveData<String>()

    var userRes = MutableLiveData<UserRes>()

    fun requestData() {

        //viewModel 携程作用域 (默认主线程)
        viewModelScope.launch(Dispatchers.Main) {

            //databing 双向绑定
            homeReposition.requestData()

        }

    }

    fun getUserInfo() {

        request({ homeReposition.getUserInfo() }, {
            userRes.value = it
        })
    }

}