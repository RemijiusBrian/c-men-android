package com.chatmen.c_men.feature_chat.domain.model

import chatmen.cmen.GetAllMessagesForChat
import com.chatmen.c_men.core.util.FormatUtil

data class Message(
    val fromMember: String,
    val text: String,
    val timestamp: String,
    val chatId: String,
    val id: String,
    val isOwnMessage: Boolean
)

fun GetAllMessagesForChat.toMessage(): Message = Message(
    fromMember = name,
    text = text,
    timestamp = FormatUtil.formatTimestamp(timestamp),
    chatId = chatId,
    id = id,
    isOwnMessage = isOwnMessage
)