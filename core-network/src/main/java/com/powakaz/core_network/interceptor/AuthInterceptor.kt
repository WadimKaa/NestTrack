package com.powakaz.core_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token : String) : Interceptor  {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

// TODO: Make work flow for auth token
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer qqqqq")
            .addHeader("Content-Type", "application/json")
            .build()


        return chain.proceed(modifiedRequest)
    }
}