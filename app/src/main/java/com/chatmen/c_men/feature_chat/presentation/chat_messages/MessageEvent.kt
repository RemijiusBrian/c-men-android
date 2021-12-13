package com.chatmen.c_men.feature_chat.presentation.chat_messages

sealed class MessageEvent {
    object InitState : MessageEvent()
    data class MessageInputChange(val message: String) : MessageEvent()
    object SendMessage : MessageEvent()
}