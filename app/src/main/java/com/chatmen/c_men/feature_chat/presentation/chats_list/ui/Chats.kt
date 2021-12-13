package com.chatmen.c_men.feature_chat.presentation.chats_list.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.chatmen.c_men.R
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatEvent
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Chats(
    state: ChatState,
    onEvent: (ChatEvent) -> Unit,
    eventsFlow: Flow<BasicUiEvent>,
    navigate: (String) -> Unit
) {
    val events by rememberUpdatedState(newValue = eventsFlow)

    LaunchedEffect(key1 = Unit) {
        events.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navigate(event.destination)
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ChatEvent.NewChatFabClick) }) {
                Icon(
                    imageVector = Icons.Outlined.PersonAdd,
                    contentDescription = stringResource(id = R.string.content_error)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
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
}