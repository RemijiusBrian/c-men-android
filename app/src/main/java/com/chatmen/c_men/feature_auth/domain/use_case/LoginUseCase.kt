package com.chatmen.c_men.feature_auth.domain.use_case

import com.chatmen.c_men.feature_auth.domain.model.LoginResult
import com.chatmen.c_men.feature_auth.domain.repository.AuthRepository
import com.chatmen.c_men.feature_auth.presentation.util.AuthError

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): LoginResult {
        val usernameError = AuthError.FieldEmpty.takeIf { username.isBlank() }
        val passwordError = AuthError.FieldEmpty.takeIf { username.isBlank() }

        if (usernameError != null || passwordError != null) {
            return LoginResult(
                usernameError = usernameError,
                passwordError = passwordError
            )
        }

        val result = repository.loginUser(username, password)
        return LoginResult(
            result = result
        )
    }
}