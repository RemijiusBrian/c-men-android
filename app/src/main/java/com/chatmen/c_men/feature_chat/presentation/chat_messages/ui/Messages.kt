package com.chatmen.c_men.feature_chat.presentation.chat_messages.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chatmen.c_men.feature_chat.presentation.chat_messages.MessageEvent
import com.chatmen.c_men.feature_chat.presentation.chat_messages.MessageState

@Composable
fun Messages(
    state: MessageState,
    messageInput: String,
    onEvent: (MessageEvent) -> Unit,
    navigateUp: () -> Unit
) {
    Scaffold {
        Column {
            LazyColumn(
                modifier = Modifier
            ) {
                items(state.messages) { message ->
                    OwnMessageItem(text = message.text)
                }
            }
            TextField(
                value = messageInput,
                onValueChange = { onEvent(MessageEvent.MessageInputChange(it)) }
            )
            Button(onClick = { onEvent(MessageEvent.SendMessage) }) {
            }
        }
    }
}