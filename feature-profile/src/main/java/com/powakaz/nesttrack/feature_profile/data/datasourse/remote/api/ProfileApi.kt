package com.powakaz.nesttrack.feature_profile.data.datasourse.remote.api

import androidx.compose.ui.graphics.Path
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApi {

    @GET("users")
    suspend fun getUsersProfile(): List<ProfileDto>

    @PUT("users/{id}")
    suspend fun updateProfile(
        @retrofit2.http.Path("id") id: Int,
        @Body profile: ProfileDto
    ): ProfileDto
}
