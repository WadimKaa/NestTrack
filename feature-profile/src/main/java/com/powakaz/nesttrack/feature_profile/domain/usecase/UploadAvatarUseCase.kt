package com.powakaz.nesttrack.feature_profile.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_profile.domain.model.Avatar
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateAvatar
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UploadAvatarUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(avatar: Avatar.Local): NetworkResult<UpdateAvatar> =
        profileRepository.updateAvatar(avatar)

}