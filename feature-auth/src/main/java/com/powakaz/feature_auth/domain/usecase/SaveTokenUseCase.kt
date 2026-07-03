package com.powakaz.feature_auth.domain.usecase

import com.powakaz.core_common.repository.TokenRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(token : String) = tokenRepository.saveToken(token)
}