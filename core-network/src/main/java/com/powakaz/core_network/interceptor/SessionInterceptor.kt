package com.powakaz.core_network.interceptor

import com.powakaz.core_common.manager.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class SessionInterceptor @Inject constructor(private val sessionManager: SessionManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code == 401) {
            runBlocking {
                sessionManager.logout()
            }
        }

        return response
    }
}