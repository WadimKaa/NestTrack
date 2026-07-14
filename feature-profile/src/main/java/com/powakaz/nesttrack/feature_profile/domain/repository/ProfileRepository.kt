package com.powakaz.nesttrack.feature_profile.domain.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.UpdateResponseDto
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateProfile
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile

interface ProfileRepository {

    suspend fun getProfile(): NetworkResult<UserProfile>

    suspend fun updateProfile(profile: UserProfile): NetworkResult<UpdateProfile>

    //suspend fun uploadAvatar(avatar: Any): NetworkResult<UserProfile>
}