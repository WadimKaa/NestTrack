package com.powakaz.nesttrack.feature_profile.data.datasourse.remote.api

import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.UpdateAvatarResponseDto
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.UserProfileResponseDto
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.UpdateProfileResponseDto
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ProfileApi {

    @GET("users")
    suspend fun getUsersProfile(): List<UserProfileResponseDto>

    @PUT("users/{id}")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body profile: UserProfileResponseDto
    ): UpdateProfileResponseDto


    @Multipart
    @POST("users/id/avatar")
    suspend fun uploadAvatar(
        @Path("id") id: Int,
        @Part avatar: MultipartBody.Part
    ): UpdateAvatarResponseDto

}
