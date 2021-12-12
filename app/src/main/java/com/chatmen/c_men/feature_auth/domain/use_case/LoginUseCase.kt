package com.chatmen.c_men.feature_auth.domain.use_case

import com.chatmen.c_men.core.domain.util.InputError
import com.chatmen.c_men.feature_auth.domain.model.LoginResult
import com.chatmen.c_men.feature_auth.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): LoginResult {
        val usernameError = InputError.FieldEmpty.takeIf { username.isBlank() }
        val passwordError = InputError.FieldEmpty.takeIf { username.isBlank() }

        if (usernameError != null || passwordError != null) {
            return LoginResult(
                usernameError = usernameError,
                passwordError = passwordError
            )
        }
        return LoginResult(
            response = repository.loginUser(username, password)
        )
    }
}