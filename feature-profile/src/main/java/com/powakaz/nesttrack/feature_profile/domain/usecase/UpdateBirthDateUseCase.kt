package com.powakaz.nesttrack.feature_profile.domain.usecase

import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateBirthDateUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(dateMillis: Long) {
        profileRepository.updateBirthDate(dateMillis)
    }
}