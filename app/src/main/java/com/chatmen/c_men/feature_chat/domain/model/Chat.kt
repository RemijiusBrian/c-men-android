package com.chatmen.c_men.feature_chat.domain.model

import chatmen.cmen.ChatEntitiy
import com.chatmen.c_men.core.util.FormatUtil

data class Chat(
    val id: String,
    val name: String,
    val description: String?,
    val timestamp: String,
    val chatIconUrl: String?,
    val lastMessage: String?
)

fun ChatEntitiy.toChat(): Chat = Chat(
    id = id,
    name = name,
    description = description,
    timestamp = FormatUtil.formatTimestamp(timestamp),
    chatIconUrl = chatIconUrl,
    lastMessage = lastMessage
)