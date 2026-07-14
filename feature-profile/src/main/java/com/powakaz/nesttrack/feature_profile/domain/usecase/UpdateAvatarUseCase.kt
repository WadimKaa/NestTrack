package com.powakaz.nesttrack.feature_profile.domain.usecase

import android.net.Uri
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateAvatarUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(avatar: Any) {
        //profileRepository.uploadAvatar(avatar)
    }
}