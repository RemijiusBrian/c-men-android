package com.chatmen.c_men.feature_chat.presentation.messages

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatmen.c_men.core.presentation.navigation.NavArgs
import com.chatmen.c_men.feature_chat.domain.use_case.MessageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
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

    // Ui State
    private val _state = mutableStateOf(MessageState())
    val state: State<MessageState> = _state

    // Messages
    val messages = useCases.getMessages(
        chatId = savedStateHandle.get<String>(NavArgs.CHAT_ID).orEmpty()
    )

    // On Event
    fun onEvent(event: MessageEvent) {
        when (event) {
            is MessageEvent.MessageInputChange -> {
                onMessageChange(event.message)
            }
            MessageEvent.SendMessage -> {
                sendMessage()
            }
        }
    }

    // Send Message
    private fun sendMessage() = viewModelScope.launch {
        useCases.sendMessage(
            messageInputState.value,
            savedStateHandle.get<String>(NavArgs.CHAT_ID),
            members = listOf("DEDM8")
        )
    }

    // Saved State Keys
    private object SavedStateKeys {
        const val MESSAGE_INPUT = "message_input"
    }
}