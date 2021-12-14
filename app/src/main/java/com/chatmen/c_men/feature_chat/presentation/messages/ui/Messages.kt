package com.chatmen.c_men.feature_chat.presentation.messages.ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.chatmen.c_men.feature_chat.domain.model.Message
import com.chatmen.c_men.feature_chat.presentation.messages.MessageEvent
import com.chatmen.c_men.feature_chat.presentation.messages.MessageState

@ExperimentalFoundationApi
@Composable
fun Messages(
    state: MessageState,
    messages: List<Message>,
    chatName: String,
    messageInput: String,
    onEvent: (MessageEvent) -> Unit,
    navigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TransparentTopAppBar(
                title = chatName,
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
                items(messages) { message ->
                    if (message.isOwnMessage) {
                        OwnMessage(
                            modifier = Modifier
                                .animateItemPlacement(),
                            text = message.text,
                            timestamp = message.timestamp
                        )
                    } else {
                        RemoteMessage(
                            modifier = Modifier
                                .animateItemPlacement(),
                            remoteUsername = message.fromMember,
                            text = message.text,
                            timestamp = message.timestamp
                        )
                    }
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