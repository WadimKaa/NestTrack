package com.powakaz.core_network.interceptor

import com.powakaz.core_common.repository.TokenRepository
import com.powakaz.core_datastore.TokenRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NoTokenException : IOException("Authentication token is missing. User must be logged in.")

class AuthInterceptor @Inject constructor(private val tokenRepository: TokenRepository) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = runBlocking {
            tokenRepository.getAccessToken().first()
        }

        if (token.isNullOrBlank()) {
            throw NoTokenException()
        }

        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .addHeader("Content-Type", "application/json")
            .build()


        return chain.proceed(modifiedRequest)
    }
}