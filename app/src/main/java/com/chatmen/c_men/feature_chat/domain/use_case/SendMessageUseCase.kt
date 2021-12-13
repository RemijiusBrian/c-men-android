package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.feature_chat.data.remote.web_socket.WsClientMessage
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository

class SendMessageUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(
        message: String,
        chatId: String?,
        members: List<String> = emptyList()
    ) {
        val clientMessage = WsClientMessage(
            text = message,
            chatId = chatId,
            members = members
        )
        repository.sendMessage(clientMessage)
    }
}