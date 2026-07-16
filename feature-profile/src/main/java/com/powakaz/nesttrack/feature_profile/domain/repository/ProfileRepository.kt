package com.powakaz.nesttrack.feature_profile.domain.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_profile.domain.model.Avatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateAvatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateProfile
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfile(userId: Int): NetworkResult<UserProfile>

    suspend fun updateProfile(profile: UserProfile): NetworkResult<UpdateProfile>

    suspend fun updateAvatar(avatar: Avatar.Local): NetworkResult<UpdateAvatar>

    suspend fun deleteAvatar(profile: UserProfile): NetworkResult<UpdateProfile>
}