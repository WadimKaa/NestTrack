package com.powakaz.nesttrack.feature_profile.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_profile.domain.model.Avatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateAvatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateProfile
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class DeleteAvatarUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(profile: UserProfile): NetworkResult<UpdateProfile> {
        return profileRepository.deleteAvatar(profile)
    }
}