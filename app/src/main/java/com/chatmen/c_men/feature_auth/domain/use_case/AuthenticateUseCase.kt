package com.chatmen.c_men.feature_auth.domain.use_case

import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): SimpleResponse = repository.authenticate()
}