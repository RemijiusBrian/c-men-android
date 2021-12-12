package com.chatmen.c_men.core.domain.states

import com.chatmen.c_men.core.domain.util.BasicError

data class TextInputState(
    val text: String = "",
    val error: BasicError? = null
)
