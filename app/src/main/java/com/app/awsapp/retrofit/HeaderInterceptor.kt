package com.app.awsapp.retrofit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @Authoer Divya
 * @Date 09-07-2022
 *
 * Information
 **/
class HeaderInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Accept","application/json")
            .build()
        return chain.proceed(request)
    }
}