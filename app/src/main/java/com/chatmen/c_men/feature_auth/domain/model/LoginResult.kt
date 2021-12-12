package com.chatmen.c_men.feature_auth.domain.model

import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.core.domain.util.BasicError

data class LoginResult(
    val usernameError: BasicError? = null,
    val passwordError: BasicError? = null,
    val response: SimpleResponse? = null
)
