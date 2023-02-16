package com.ulb.code.ui.navigation.home.net

import com.ulb.code.config.ApiServiceManager
import com.ulb.common.net.HttpResult
import com.ulb.code.res.UserRes
import com.ulb.code.ui.main.TestInfo
import retrofit2.Call

class HomeReposition {

    private val service by lazy { ApiServiceManager.createApiService(IHomeApi::class.java) }

    fun requestData(): Call<TestInfo> {

        return service
            .getWeatherInfo()

    }

    /**
     * 获取用户信息
     */
    suspend fun getUserInfo(): HttpResult<UserRes> {

        return service.getUserInfo()
    }

}