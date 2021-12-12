package com.chatmen.c_men.core.data.util

import com.chatmen.c_men.core.presentation.util.UiText

typealias SimpleResponse = Response<Unit>

sealed class Response<T>(
    val data: T? = null,
    val message: UiText? = null
) {
    class Error<T>(uiText: UiText, data: T? = null) : Response<T>(data, uiText)
    class Success<T>(data: T) : Response<T>(data)
}