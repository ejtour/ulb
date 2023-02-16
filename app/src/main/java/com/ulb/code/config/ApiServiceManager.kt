package com.ulb.code.config

import com.google.gson.GsonBuilder
import com.ulb.common.net.GsonTypeAdapterFactory
import com.ulb.common.net.HttpClientFactory
import com.ulb.common.utils.LogUtils
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceManager {

    private val okHttpClient by lazy {

        getClient()
    }

    fun <T> createApiService(tClass: Class<T>?, host: String = UrlConfig.wmsHost): T {

        return Retrofit.Builder()
            .baseUrl(host)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapterFactory(
                        GsonTypeAdapterFactory()
                    ).create()
                )
            )
            .build()
            .create(tClass)
    }

    /**
     *
     */
    private fun getClient(): OkHttpClient {

        var builder = HttpClientFactory.getHttpClientBuilder()
        builder.cookieJar(object : CookieJar {

            private val cookieStore = HashMap<String, List<Cookie>>()
            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookieStore[url.host()] = cookies
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                var storeList: List<Cookie>? = cookieStore[url.host()]
                val cookies = mutableListOf<Cookie>()
                if (!storeList.isNullOrEmpty()) {
                    cookies.addAll(storeList);
                }
                var cookie_ = Cookie.parse(url, "access_token=" + UserConfig.accessToken)
                cookie_?.run {
                    cookies.add(cookie_)
                }
                LogUtils.d("okhttp", "cookies = $cookies")
                return cookies.toList()
            }
        })
        builder.addInterceptor(ParamsInterceptor())
        builder.addInterceptor(HeaderInterceptor())
        builder.addInterceptor(HttpLoggingInterceptor { message ->
            LogUtils.d(
                "okhttp",
                message
            )
        }.setLevel(HttpLoggingInterceptor.Level.BODY))
        return builder.build()
    }

}