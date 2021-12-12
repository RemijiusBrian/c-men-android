package com.chatmen.c_men.core.data.util

import com.chatmen.c_men.core.presentation.util.UiText

sealed class Resource<T>(
    val data: T? = null,
    val message: UiText? = null
) {
    class Error<T>(uiText: UiText, data: T? = null) : Resource<T>(data, uiText)
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}