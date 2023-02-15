package com.ulb.code.ui.main.net

import com.ulb.code.HttpResult
import com.ulb.code.res.UserRes
import com.ulb.code.ui.main.TestInfo
import com.ulb.common.net.HttpClientFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMainApi {

    @GET("weather/info")
    fun getWeatherInfo(): Call<TestInfo>

    /**
     * 获取用户信息
     */
    @POST("user/getUserInfo")
    suspend fun getUserInfo(): HttpResult<UserRes>
}