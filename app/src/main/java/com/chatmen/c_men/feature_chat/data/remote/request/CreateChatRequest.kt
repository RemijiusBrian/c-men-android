package com.chatmen.c_men.feature_chat.data.remote.request

data class CreateChatRequest(
    val name: String?,
    val members: List<String>,
    val description: String?,
    val chatIconUrl: String?
)
