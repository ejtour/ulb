package com.ulb.common.net;

import com.ulb.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClientFactory {

    private static final int DEFAULT_TIMEOUT = 300;

    private static class Instance {
        static final OkHttpClient.Builder INSTANCE = createBuilder();
    }

    public static OkHttpClient.Builder getHttpClientBuilder() {
        return Instance.INSTANCE;
    }

    private static OkHttpClient.Builder createBuilder() {
//        HttpsUtils.SSLParams sslParams = null;
//        try {
//            //todo 此处若发生crash catch 中不同样crash了么
//            sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//        } catch (Exception e) {
//            LogUtils.e("okhttp", "ssl", e);
//            sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//        }

        return new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .hostnameVerifier((hostname, session) -> true)
                .addNetworkInterceptor(chain -> {
                    String traceID = chain.request().header("traceID");
                    Response response = chain.proceed(chain.request());
                    if (response.code() == 302) {
                        String json = "{\"traceID\":\"" + traceID + "\",\"code\":\"0011111100000001\"," +
                                "\"msg\":\"登录过期，请重新登录\",\"success\":false}";
                        MediaType contentType = response.body().contentType();
                        ResponseBody body = ResponseBody.create(contentType, json);
                        return response.newBuilder().body(body).code(200).build();
                    }
                    return response;
                });
    }
}
