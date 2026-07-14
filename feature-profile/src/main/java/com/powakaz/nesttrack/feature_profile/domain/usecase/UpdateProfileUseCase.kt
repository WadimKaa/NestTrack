package com.powakaz.nesttrack.feature_profile.domain.usecase

import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileDto
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(profile: UserProfile) {
        profileRepository.updateProfile(profile)
    }
}