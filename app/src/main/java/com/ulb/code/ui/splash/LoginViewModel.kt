package com.ulb.code.ui.splash

import androidx.lifecycle.MutableLiveData
import com.ulb.common.request
import com.ulb.code.res.LoginRes
import com.ulb.code.ui.splash.net.LoginReposition
import com.ulb.common.base.BaseViewModel

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