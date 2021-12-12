package com.chatmen.c_men.feature_auth.presentation.login

sealed class LoginEvent {
    data class UsernameChange(val username: String) : LoginEvent()
    data class PasswordChange(val password: String) : LoginEvent()
    object Login : LoginEvent()
}