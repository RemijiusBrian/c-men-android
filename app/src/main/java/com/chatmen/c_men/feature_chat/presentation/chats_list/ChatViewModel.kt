package com.chatmen.c_men.feature_chat.presentation.chats_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.core.domain.util.Refresh
import com.chatmen.c_men.core.presentation.navigation.Destination
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.core.presentation.util.UiText
import com.chatmen.c_men.feature_chat.domain.use_case.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCases: ChatUseCases
) : ViewModel() {

    // Refresh Trigger
    private val _refresh = Channel<Refresh>()
    private val refresh = _refresh.receiveAsFlow()

    // State
    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state

    // Events
    private val _events = Channel<BasicUiEvent>()
    val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch { _refresh.send(Refresh.NORMAL) }
        onEvent(ChatEvent.InitState)
    }

    // On Event
    fun onEvent(event: ChatEvent) {
        when (event) {
            ChatEvent.InitState -> {
                // authenticateUser()
                collectChats()
            }
            ChatEvent.NewChatFabClick -> {
                viewModelScope.launch {
                    _events.send(UiEvent.Navigate(Destination.Members.route))
                }
            }
            ChatEvent.Refresh -> {
                refresh()
            }
            is ChatEvent.ChatClick -> {
                viewModelScope.launch {
                    _events.send(
                        UiEvent.Navigate(
                            Destination.Messages.withArgs(event.chatId, event.chatName)
                        )
                    )
                }
            }
            ChatEvent.AuthenticateAgain -> {
                viewModelScope.launch {
                    _events.send(ChatUiEvent.AuthenticateAgain)
                }
            }
        }
    }

    // Collect Chats
    private fun collectChats() {
        refresh.flatMapLatest { refresh ->
            useCases.getChats(refresh)
        }.onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    _events.send(
                        UiEvent.ShowSnackbar(resource.message ?: UiText.unknownError())
                    )
                    _state.value = state.value.copy(
                        chats = resource.data.orEmpty(),
                        refreshState = state.value.refreshState.apply {
                            isRefreshing = false
                        }
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        chats = resource.data.orEmpty(),
                        refreshState = state.value.refreshState.apply {
                            isRefreshing = true
                        }
                    )
                }
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        chats = resource.data.orEmpty(),
                        refreshState = state.value.refreshState.apply {
                            isRefreshing = false
                        }
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    // Join Chat
    private suspend fun joinChat() {
        when (val response = useCases.joinChat()) {
            is Response.Error -> {
                _events.send(UiEvent.ShowSnackbar(response.message ?: UiText.unknownError()))
            }
            is Response.Success -> {
                useCases.collectSocketMessages()
            }
        }
    }

    // Disconnect Chat
    private fun disconnectChat() = viewModelScope.launch {
        useCases.disconnectChat()
    }

    // Refresh
    private fun refresh() = viewModelScope.launch {
        _refresh.send(Refresh.FORCE)
    }

    // Authenticate User
    private fun authenticateUser() = viewModelScope.launch {
        when (useCases.authenticate()) {
            is Response.Error -> {
                _state.value = state.value.copy(
                    isAuthenticationLostDialogShown = true
                )
            }
            is Response.Success -> {
                joinChat()
            }
        }
    }

    //
    override fun onCleared() {
        super.onCleared()
        disconnectChat()
    }

    sealed class ChatUiEvent : BasicUiEvent() {
        object AuthenticateAgain : ChatUiEvent()
    }
}