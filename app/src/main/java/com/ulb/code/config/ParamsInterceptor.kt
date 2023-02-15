package com.ulb.code.config

import okhttp3.*
import okio.Buffer
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * 公共参数拦截器
 */
class ParamsInterceptor : Interceptor {

    var maxRetry = 0
    private var retryNum = 1

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val requestHeader = originRequest.newBuilder().build()
        val builder = requestHeader.url().newBuilder()
        builder.addQueryParameter("plat", "99")
        if (!UserConfig.accessToken.isNullOrEmpty()) {
            builder.addQueryParameter("accessToken", UserConfig.accessToken)
        }
        var newBody: RequestBody? = null
        if (originRequest.body() is FormBody) {
            // formBody 请求体不需要新增参数
        } else {
            val paramsJson: String = addParams(originRequest)
            newBody = RequestBody.create(originRequest.body()?.contentType(), paramsJson)
        }
        val requestEnd: Request = if (newBody != null) {
            requestHeader.newBuilder().post(newBody).url(builder.build()).build()
        } else {
            requestHeader.newBuilder().url(builder.build()).build()
        }

        var response = chain.proceed(requestEnd)
//        while (!response.isSuccessful && retryNum < maxRetry) {
//            retryNum++
//            response = chain.proceed(requestEnd)
//        }
        return response
    }

    private fun addParams(request: Request): String {
        val requestBody = request.body()
        val buffer = Buffer()
        try {
            requestBody?.writeTo(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        var charset = Charset.forName("UTF-8")
        val contentType = requestBody?.contentType()
        if (contentType != null) {
            charset = contentType.charset(StandardCharsets.UTF_8)
            if (charset != null) {
                //读取原请求参数内容
                val requestParams = buffer.readString(charset)
                try {
                    val jsonObject = JSONTokener(requestParams).nextValue()
                    if (jsonObject is JSONObject) {
                        jsonObject.put("groupID", "${UserConfig.groupId}")
                    } else if (jsonObject is JSONArray) {
                        for (i in 0 until jsonObject.length()) {
                            val `object` = jsonObject.getJSONObject(i)
                            (`object` as JSONObject).put(
                                "groupID",
                                "${UserConfig.groupId}"
                            )
                        }
                    }
                    return jsonObject.toString()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return ""
    }

}