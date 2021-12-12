package com.chatmen.c_men.feature_chat.presentation.chats_list.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatEvent
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatState

@Composable
fun Chats(
    state: ChatState,
    onEvent: (ChatEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = state.chats) { chat ->
            ChatItem(
                name = chat.name.orEmpty(),
                iconUrl = chat.chatIconUrl,
                lastMessage = chat.lastMessage,
                timestamp = chat.timestamp
            )
        }
    }

}