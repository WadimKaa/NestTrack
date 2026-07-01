package com.powakaz.nesttrack.feature_profile.domain.repository

import android.net.Uri
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(): Flow<UserProfile?>

    suspend fun updateName(name: String)

    suspend fun updateBirthDate(dateMillis: Long)

    suspend fun updateAvatar(uri: Uri)
}