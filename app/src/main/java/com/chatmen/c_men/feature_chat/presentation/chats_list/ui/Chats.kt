package com.chatmen.c_men.feature_chat.presentation.chats_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chatmen.c_men.R
import com.chatmen.c_men.core.presentation.components.AuthenticationLostDialog
import com.chatmen.c_men.core.presentation.components.ProfileIcon
import com.chatmen.c_men.core.presentation.components.TransparentTopAppBar
import com.chatmen.c_men.core.presentation.ui.theme.PaddingMedium
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.core.presentation.util.asString
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatEvent
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatState
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatViewModel
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
    navigate: (String) -> Unit,
    reAuthenticateUser: () -> Unit
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
                ChatViewModel.ChatUiEvent.AuthenticateAgain -> {
                    reAuthenticateUser()
                }
            }
        }
    }
    SwipeRefresh(state = state.refreshState, onRefresh = { onEvent(ChatEvent.Refresh) }) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                item {
                    TransparentTopAppBar(
                        navigationIcon = {
                            IconButton(onClick = { }) {
                                ProfileIcon(
                                    contentDescription = "",
                                    size = 16.dp
                                )
                            }
                        }
                    )
                }

                items(state.chats) { chat ->
                    ChatItem(
                        name = chat.name,
                        iconUrl = chat.chatIconUrl,
                        lastMessage = chat.lastMessage,
                        timestamp = chat.timestamp,
                        onClick = {
                            onEvent(ChatEvent.ChatClick(chat.id, chat.name))
                        }
                    )
                }
            }
            FloatingActionButton(
                onClick = { onEvent(ChatEvent.NewChatFabClick) },
                modifier = Modifier
                    .padding(PaddingMedium)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Chat,
                    contentDescription = stringResource(id = R.string.content_new_chat)
                )
            }
        }
    }

    if (state.isAuthenticationLostDialogShown) {
        AuthenticationLostDialog(
            onOKClick = { onEvent(ChatEvent.AuthenticateAgain) }
        )
    }
}