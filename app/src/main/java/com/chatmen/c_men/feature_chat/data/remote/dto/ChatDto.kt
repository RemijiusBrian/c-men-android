package com.chatmen.c_men.feature_chat.data.remote.dto

import chatmen.cmen.ChatEntitiy

data class ChatDto(
    val chatId: String,
    val name: String,
    val description: String?,
    val timestamp: Long,
    val chatIconUrl: String?,
    val lastMessage: String?,
) {
    fun toChatEntity(): ChatEntitiy = ChatEntitiy(
        id = chatId,
        name = name,
        description = description,
        timestamp = timestamp,
        chatIconUrl = chatIconUrl,
        lastMessage = lastMessage
    )
}