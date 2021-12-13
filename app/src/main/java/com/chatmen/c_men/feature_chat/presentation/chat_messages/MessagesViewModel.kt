package com.chatmen.c_men.feature_chat.presentation.chat_messages

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.domain.util.Refresh
import com.chatmen.c_men.core.presentation.navigation.NavArgs
import com.chatmen.c_men.feature_chat.domain.use_case.MessageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val useCases: MessageUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Message Input State
    private val _messageInputState = mutableStateOf(
        savedStateHandle.get<String>(SavedStateKeys.MESSAGE_INPUT).orEmpty()
    )
    val messageInputState: State<String> = _messageInputState
    private fun onMessageChange(value: String) {
        _messageInputState.value = value
        savedStateHandle.set(SavedStateKeys.MESSAGE_INPUT, value)
    }

    // Refresh Trigger
    private val _refresh = Channel<Refresh>()
    private val refresh = _refresh.receiveAsFlow()

    // Ui State
    private val _state = mutableStateOf(MessageState())
    val state: State<MessageState> = _state

    init {
        viewModelScope.launch { _refresh.send(Refresh.NORMAL) }
        onEvent(MessageEvent.InitState)
    }

    // On Event
    fun onEvent(event: MessageEvent) {
        when (event) {
            MessageEvent.InitState -> {
                collectMessages()
            }
            is MessageEvent.MessageInputChange -> {
                onMessageChange(event.message)
            }
            MessageEvent.SendMessage -> {
                sendMessage()
            }
        }
    }

    // Collect Messages
    private fun collectMessages() {
        refresh.flatMapLatest { refresh ->
            useCases.getMessages(
                chatId = savedStateHandle.get<String>(NavArgs.CHAT_ID).orEmpty(),
                refresh = refresh
            )
        }.onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        messages = resource.data.orEmpty(),
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        messages = resource.data.orEmpty(),
                    )
                }
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        messages = resource.data.orEmpty(),
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    // Send Message
    private fun sendMessage() = viewModelScope.launch {
        useCases.sendMessage(
            messageInputState.value,
            savedStateHandle.get<String>(NavArgs.CHAT_ID)
        )
    }

    // Saved State Keys
    private object SavedStateKeys {
        const val MESSAGE_INPUT = "message_input"
    }
}