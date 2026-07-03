package com.powakaz.feature_auth.data.remote

import com.powakaz.feature_auth.data.remote.model.token_check.TokenCheckBodyRequest
import com.powakaz.feature_auth.data.remote.model.token_check.TokenCheckResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkTokenApi {

    @POST("/api/v1/auth/login")
    suspend fun checkToken(@Body tokenCheckBodyRequest: TokenCheckBodyRequest) : TokenCheckResponseDto
}