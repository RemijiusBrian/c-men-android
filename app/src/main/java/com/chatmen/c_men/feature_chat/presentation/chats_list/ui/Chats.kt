package com.chatmen.c_men.feature_chat.presentation.chats_list.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.chatmen.c_men.R
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.core.presentation.util.asString
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatEvent
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatState
import com.google.accompanist.swiperefresh.SwipeRefresh
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun Chats(
    state: ChatState,
    onEvent: (ChatEvent) -> Unit,
    eventsFlow: Flow<BasicUiEvent>,
    navigate: (String) -> Unit
) {
    val events by rememberUpdatedState(newValue = eventsFlow)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        events.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navigate(event.destination)
                }
                is UiEvent.ShowSnackbar -> {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = event.uiText.asString(context)
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ChatEvent.NewChatFabClick) }) {
                Icon(
                    imageVector = Icons.Outlined.Chat,
                    contentDescription = stringResource(id = R.string.content_new_chat)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        SwipeRefresh(
            state = state.refreshState,
            onRefresh = { onEvent(ChatEvent.Refresh) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = state.chats) { chat ->
                    ChatItem(
                        modifier = Modifier
                            .fillMaxWidth(),
                        name = chat.name,
                        iconUrl = chat.chatIconUrl,
                        lastMessage = chat.lastMessage,
                        timestamp = chat.timestamp,
                        onClick = { onEvent(ChatEvent.ChatClick(chat.id, chat.name)) }
                    )
                }
            }
        }
    }
}