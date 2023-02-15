package com.ulb.code.ui.splash.net

import com.ulb.code.HttpResult
import com.ulb.code.res.LoginRes
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @des 登录相关接口
 */
interface ILoginApi {

    @FormUrlEncoded
    @POST("loginMobile")
    suspend fun loginMobile(
        @Field("groupLoginName") groupId: String,
        @Field("userId") userId: String,
        @Field("password") password: String
    ): HttpResult<LoginRes>
}