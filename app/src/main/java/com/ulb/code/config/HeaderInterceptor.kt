package com.ulb.code.config

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * @des 头部基础参数
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("app_c", "android_wms")
            .addHeader("app_v", "2.6.8")
            .addHeader("app_vcode", "10086") //解决dev分支版本号比master低
            .addHeader("Content-Type", "application/json; charset=UTF-8")
            .addHeader("groupID", UserConfig.groupId)
            .addHeader("Accept-Encoding", "identity")
            .addHeader(
                "orgID",
//                if (UserConfig.getHouse() == null) "" else "" + UserConfig.getHouse().getOrgID()
                ""
            )
            .addHeader("traceID", UUID.randomUUID().toString())
            .build()

        return chain.proceed(builder.build())
    }
}