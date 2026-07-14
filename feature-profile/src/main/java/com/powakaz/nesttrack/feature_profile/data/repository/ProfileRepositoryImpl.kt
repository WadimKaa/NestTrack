package com.powakaz.nesttrack.feature_profile.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.api.ProfileApi
import com.powakaz.nesttrack.feature_profile.data.mapper.toDomain
import com.powakaz.nesttrack.feature_profile.data.mapper.toDto
import com.powakaz.nesttrack.feature_profile.di.PrivateProfileApi
import com.powakaz.nesttrack.feature_profile.di.PublicProfileApi
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateAvatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateProfile
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    //private val dao: UserProfileDAO,
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
                it.id == CURRENT_USER_ID
            } ?: throw Exception("User ID not found")

            profile.toDomain()
        }

        return result
    }


    override suspend fun updateProfile(profile: UserProfile): NetworkResult<UpdateProfile> {

        val result = safeApiCall {
            val updateResponse  = privateApi.updateProfile(
                id = CURRENT_USER_ID,
                profile = profile.toDto()
            )
            updateResponse .toDomain()
        }
        return result
    }

    override suspend fun updateAvatar(avatar: Any): NetworkResult<UpdateAvatar> {

        val result = safeApiCall {
            val updateAvatar = privateApi.uploadAvatar(
                id = CURRENT_USER_ID,
                avatar = avatar
            )
        }

        return result
    }


    companion object {
        private const val CURRENT_USER_ID = 2
    }

}