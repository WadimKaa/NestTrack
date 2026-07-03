package com.powakaz.nesttrack.feature_profile.data.repository

import android.net.Uri
import com.powakaz.core_common.repository.TokenRepository
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.dao.UserProfileDAO
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val dao: UserProfileDAO,
    private val tokenRepository: TokenRepository
) : ProfileRepository
{
    override fun getProfile(): Flow<UserProfile?> {
    }

    override suspend fun updateName(name: String) {
    }

    override suspend fun updateBirthDate(dateMillis: Long) {
    }

    override suspend fun updateAvatar(uri: Uri) {
    }
}