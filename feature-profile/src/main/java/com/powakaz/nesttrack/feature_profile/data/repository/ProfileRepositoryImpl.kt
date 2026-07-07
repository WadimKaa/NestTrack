package com.powakaz.nesttrack.feature_profile.data.repository

import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.copy
import com.powakaz.core_common.repository.TokenRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.dao.UserProfileDAO
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.api.ProfileApi
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileDto
import com.powakaz.nesttrack.feature_profile.data.mapper.toDomain
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    //private val dao: UserProfileDAO,
    private val api: ProfileApi
) : ProfileRepository
{
    override suspend fun getProfile(): NetworkResult<ProfileDto> {

        val result = safeApiCall {
            val profileList = api.getUsersProfile()

            profileList.find {
                it.id == CURRENT_USER_ID
            } ?: throw Exception("User ID not found")

        }


        return result
    }

    override suspend fun updateName(name: String) {
    }

    override suspend fun updateBirthDate(dateMillis: Long) {
    }

    override suspend fun updateAvatar(uri: Uri) {
    }

    companion object {
        private const val CURRENT_USER_ID = 2
    }

}