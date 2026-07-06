package com.powakaz.feature_auth.domain.usecase

import com.powakaz.core_common.repository.UserIdRepository
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(private val userIdRepository: UserIdRepository) {
    suspend operator fun invoke(userId : Int) = userIdRepository.saveUserId(userId)
}