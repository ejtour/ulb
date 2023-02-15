package com.ulb.code.ui.main.net

import com.ulb.code.HttpResult
import com.ulb.code.config.ApiServiceManager
import com.ulb.code.res.UserRes
import com.ulb.code.ui.main.TestInfo
import retrofit2.Call

class MainReposition {

    private val service by lazy { ApiServiceManager.createApiService(IMainApi::class.java) }

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