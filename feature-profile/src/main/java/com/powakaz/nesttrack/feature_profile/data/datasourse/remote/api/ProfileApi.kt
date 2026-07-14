package com.powakaz.nesttrack.feature_profile.data.datasourse.remote.api

import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.AvatarDto
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileDto
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ProfileApi {

    @GET("users")
    suspend fun getUsersProfile(): List<ProfileDto>

    @PUT("users/{id}")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body profile: ProfileDto
    ): ProfileDto


    @Multipart
    @POST("users/id/avatar")
    suspend fun uploadAvatar(
        @Path("id") id: Int,
        @Part avatar: MultipartBody.Part
    ): AvatarDto

}
