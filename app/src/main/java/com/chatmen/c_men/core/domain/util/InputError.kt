package com.chatmen.c_men.core.domain.util

sealed class InputError : BasicError() {
    object FieldEmpty : InputError()
}
