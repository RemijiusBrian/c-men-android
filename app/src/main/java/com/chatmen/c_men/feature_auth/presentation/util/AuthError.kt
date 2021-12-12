package com.chatmen.c_men.feature_auth.presentation.util

import com.chatmen.c_men.core.domain.util.BaseError

sealed class AuthError : BaseError() {
    object FieldEmpty : AuthError()
}
