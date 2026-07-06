package com.powakaz.nesttrack.feature_profile.data.repository

import android.net.Uri
import com.powakaz.core_common.repository.TokenRepository
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.dao.UserProfileDAO
import com.powakaz.nesttrack.feature_profile.data.mapper.toDomain
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ProfileRepositoryImpl(
    //private val dao: UserProfileDAO,
) : ProfileRepository
{

    companion object {
        private const val CURRENT_USER_ID = 2
    }


    override fun getProfile(): Flow<UserProfile?> {
    }

    override suspend fun updateName(name: String) {
    }

    override suspend fun updateBirthDate(dateMillis: Long) {
    }

    override suspend fun updateAvatar(uri: Uri) {
    }
}