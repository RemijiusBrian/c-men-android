package com.chatmen.c_men.feature_chat.data.remote.web_socket

data class WsClientMessage(
    val text: String,
    val chatId: String?,
    val members: List<String> = emptyList()
)