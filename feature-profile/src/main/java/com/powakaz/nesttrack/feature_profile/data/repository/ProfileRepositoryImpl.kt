package com.powakaz.nesttrack.feature_profile.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.api.ProfileApi
import com.powakaz.nesttrack.feature_profile.data.mapper.AvatarMultipartMapper
import com.powakaz.nesttrack.feature_profile.data.mapper.toDomain
import com.powakaz.nesttrack.feature_profile.data.mapper.toDto
import com.powakaz.nesttrack.feature_profile.di.PrivateProfileApi
import com.powakaz.nesttrack.feature_profile.di.PublicProfileApi
import com.powakaz.nesttrack.feature_profile.domain.model.Avatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateAvatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateProfile
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    //private val dao: UserProfileDAO,
    private val userIdRepository: UserIdRepository,
    private val avatarMultipartMapper: AvatarMultipartMapper,
    @PublicProfileApi
    val publicApi: ProfileApi,
    @PrivateProfileApi
    val privateApi: ProfileApi
) : ProfileRepository
{
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getProfile(): NetworkResult<UserProfile> {
        val result = safeApiCall {

            val profileList = publicApi.getUsersProfile()

            val profile = profileList.find {
                it.id == userIdRepository.getUserId().first()
            } ?: throw Exception("User ID not found")

            profile.toDomain()
        }

        return result
    }


    override suspend fun updateProfile(profile: UserProfile): NetworkResult<UpdateProfile> {

        val result = safeApiCall {
            val updateResponse  = privateApi.updateProfile(
                id = profile.id,
                profile = profile.toDto()
            )
            updateResponse.toDomain()
        }
        return result
    }

    override suspend fun updateAvatar(avatar: Avatar.Local): NetworkResult<UpdateAvatar> {

        val result = safeApiCall {
            val updateAvatar = privateApi.uploadAvatar(
                id = userIdRepository.getUserId().first(),
                avatar = avatarMultipartMapper.map(avatar.path)
            )
            updateAvatar.toDomain()
        }

        return result
    }

    override suspend fun deleteAvatar(profile: UserProfile): NetworkResult<UpdateProfile> {
        val result = safeApiCall {
            val updateResponse  = privateApi.updateProfile(
                id = userIdRepository.getUserId().first(),
                profile = profile.toDto()
            )
            updateResponse.toDomain()
        }
        return result
    }
}