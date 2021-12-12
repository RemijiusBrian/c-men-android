package com.chatmen.c_men.feature_auth.presentation.util

import com.chatmen.c_men.core.domain.util.BasicError

sealed class AuthError : BasicError() {
    object FieldEmpty : AuthError()
}
