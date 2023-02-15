package com.ulb.code.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulb.code.base.BaseViewModel
import com.ulb.code.request
import com.ulb.code.res.LoginRes
import com.ulb.code.ui.splash.net.LoginReposition
import io.reactivex.Observable
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    var loginRes = MutableLiveData<LoginRes>()

    fun loginByPsd(groupName: String, userName: String, userPsd: String) {

        request({
            LoginReposition().loginByPsd(groupName, userName, userPsd)
        }, {
            loginRes.value = it
        })
    }
}