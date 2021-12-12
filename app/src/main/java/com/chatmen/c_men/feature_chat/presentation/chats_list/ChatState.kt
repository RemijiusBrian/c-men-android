package com.chatmen.c_men.feature_chat.presentation.chats_list

import com.chatmen.c_men.feature_chat.domain.model.Chat

data class ChatState(
    val chats: List<Chat> = emptyList(),
    val isLoading: Boolean = false
)
