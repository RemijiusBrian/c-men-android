package com.chatmen.c_men.feature_chat.presentation.chat_messages

import com.chatmen.c_men.feature_chat.domain.model.Message

data class MessageState(
    val messages: List<Message> = emptyList(),
)
