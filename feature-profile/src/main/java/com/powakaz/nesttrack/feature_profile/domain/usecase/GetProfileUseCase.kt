package com.powakaz.nesttrack.feature_profile.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileDto
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
){
    suspend operator fun invoke(): NetworkResult<ProfileDto> = profileRepository.getProfile()
}