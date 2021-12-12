package com.chatmen.c_men.feature_auth.domain.model

import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.core.domain.util.BaseError

data class LoginResult(
    val usernameError: BaseError? = null,
    val passwordError: BaseError? = null,
    val result: SimpleResponse? = null
)
