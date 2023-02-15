package com.ulb.code.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulb.code.base.BaseViewModel
import com.ulb.code.request
import com.ulb.code.res.UserRes
import com.ulb.code.ui.main.net.MainReposition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    private val mainReposition = MainReposition()

    var nameLiveData = MutableLiveData<String>()

    var userRes = MutableLiveData<UserRes>()

    fun requestData() {

        //viewModel 携程作用域 (默认主线程)
        viewModelScope.launch(Dispatchers.Main) {

            //databing 双向绑定
            mainReposition.requestData()

        }

    }

    fun getUserInfo() {

        request({ mainReposition.getUserInfo() }, {
            userRes.value = it
        })
    }

}