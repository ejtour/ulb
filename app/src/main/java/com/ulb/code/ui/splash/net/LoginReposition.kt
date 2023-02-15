package com.ulb.code.ui.splash.net

import com.ulb.code.HttpResult
import com.ulb.code.config.ApiServiceManager
import com.ulb.code.config.UrlConfig
import com.ulb.code.res.LoginRes
import io.reactivex.Observable

/**
 * @des 登录相关
 */
class LoginReposition {

    suspend fun loginByPsd(groupName: String, userName: String, userPsd: String): HttpResult<LoginRes> {

        return ApiServiceManager.createApiService(ILoginApi::class.java,UrlConfig.loginHost)
            .loginMobile(groupName, userName, userPsd)
    }
}