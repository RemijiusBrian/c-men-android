package com.chatmen.c_men.feature_chat.presentation.chats_list

sealed class ChatEvent {
    object InitState : ChatEvent()
    object NewChatFabClick : ChatEvent()
    object Refresh : ChatEvent()
    data class ChatClick(val chatId: String, val chatName: String) : ChatEvent()
}