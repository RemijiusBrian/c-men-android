package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.feature_chat.domain.model.Message
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class GetMessagesUseCase(
    private val repository: ChatRepository
) {
    operator fun invoke(chatId: String): Flow<List<Message>> =
        repository.getAllMessagesForChat(chatId)
}