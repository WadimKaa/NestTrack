package com.powakaz.nesttrack.feature_profile.domain.usecase

import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import java.time.LocalDate
import javax.inject.Inject

class UpdateBirthDateUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(date: LocalDate) {
        profileRepository.updateBirthDate(date)
    }
}