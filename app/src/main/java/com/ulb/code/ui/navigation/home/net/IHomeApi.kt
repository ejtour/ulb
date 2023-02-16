package com.ulb.code.ui.navigation.home.net

import com.ulb.common.net.HttpResult
import com.ulb.code.res.UserRes
import com.ulb.code.ui.main.TestInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface IHomeApi {

    @GET("weather/info")
    fun getWeatherInfo(): Call<TestInfo>

    /**
     * 获取用户信息
     */
    @POST("user/getUserInfo")
    suspend fun getUserInfo(): HttpResult<UserRes>
}