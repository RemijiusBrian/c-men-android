package com.chatmen.c_men.feature_chat.data.remote.dto

data class MessageDto(
    val fromUsername: String,
    val text: String,
    val timestamp: Long,
    val chatId: String,
    val id: String
)
