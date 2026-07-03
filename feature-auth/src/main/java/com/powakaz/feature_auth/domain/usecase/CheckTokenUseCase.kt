package com.powakaz.feature_auth.domain.usecase

import com.powakaz.feature_auth.domain.repository.TokenCheckRepository
import javax.inject.Inject


class CheckTokenUseCase @Inject constructor(val tokenCheckRepository: TokenCheckRepository) {
    suspend operator fun invoke(token : String) = tokenCheckRepository.checkToken(token)
}