package com.chatmen.c_men.feature_chat.presentation.chats_list

import com.chatmen.c_men.feature_chat.domain.model.Chat
import com.google.accompanist.swiperefresh.SwipeRefreshState

data class ChatState(
    val chats: List<Chat> = emptyList(),
    val refreshState: SwipeRefreshState = SwipeRefreshState(false),
)