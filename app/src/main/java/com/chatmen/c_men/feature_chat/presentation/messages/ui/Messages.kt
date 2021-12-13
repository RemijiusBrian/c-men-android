package com.chatmen.c_men.feature_chat.presentation.messages.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chatmen.c_men.R
import com.chatmen.c_men.core.presentation.components.BackArrowButton
import com.chatmen.c_men.core.presentation.components.TransparentTopAppBar
import com.chatmen.c_men.core.presentation.navigation.Destination
import com.chatmen.c_men.feature_chat.presentation.messages.MessageEvent
import com.chatmen.c_men.feature_chat.presentation.messages.MessageState

@Composable
fun Messages(
    state: MessageState,
    messageInput: String,
    onEvent: (MessageEvent) -> Unit,
    navigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TransparentTopAppBar(
                titleRes = Destination.Messages.titleRes,
                navigationIcon = { BackArrowButton(onClick = navigateUp) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                reverseLayout = true
            ) {
                items(state.messages) { message ->
                    OwnMessageItem(text = message.text)
                }
            }
            MessageInput(
                messageInput = messageInput,
                onMessageInputChange = { onEvent(MessageEvent.MessageInputChange(it)) },
                modifier = Modifier
                    .fillMaxWidth(),
                placeHolder = R.string.hint_send_message,
                onSend = { onEvent(MessageEvent.SendMessage) }
            )
        }
    }
}