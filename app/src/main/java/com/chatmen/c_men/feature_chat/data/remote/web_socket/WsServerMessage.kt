package com.chatmen.c_men.feature_chat.data.remote.web_socket

data class WsServerMessage(
    val fromUsername: String,
    val toUsername: String,
    val text: String,
    val timestamp: Long,
    val chatId: String,
)