package com.powakaz.nesttrack.feature_profile.domain.usecase

import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateNameUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(name: String) {
        if (name.isNotBlank()) {
            profileRepository.updateName(name)
        }
    }
}