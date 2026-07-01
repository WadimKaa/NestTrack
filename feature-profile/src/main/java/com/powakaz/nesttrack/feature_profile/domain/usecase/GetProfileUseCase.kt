package com.powakaz.nesttrack.feature_profile.domain.usecase

import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
){
    operator fun invoke(): Flow<UserProfile?> {
        return profileRepository.getProfile()
    }
}