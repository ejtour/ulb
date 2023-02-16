package com.ulb.code.ui.splash.net

import com.ulb.code.config.ApiServiceManager
import com.ulb.common.net.HttpResult
import com.ulb.code.config.UrlConfig
import com.ulb.code.res.LoginRes

/**
 * @des 登录相关
 */
class LoginReposition {

    suspend fun loginByPsd(groupName: String, userName: String, userPsd: String): HttpResult<LoginRes> {

        return ApiServiceManager.createApiService(ILoginApi::class.java,UrlConfig.loginHost)
            .loginMobile(groupName, userName, userPsd)
    }
}