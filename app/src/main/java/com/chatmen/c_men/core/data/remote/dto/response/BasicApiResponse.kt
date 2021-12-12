package com.chatmen.c_men.core.data.remote.dto.response

data class BasicApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T? = null
)
