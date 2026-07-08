package com.powakaz.nesttrack.feature_profile.domain.repository

import android.net.Uri
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileDto
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface ProfileRepository {

    suspend fun getProfile(): NetworkResult<UserProfile>

    suspend fun updateName(name: String)

    suspend fun updateBirthDate(date: LocalDate)

    suspend fun updateAvatar(uri: Uri)
}