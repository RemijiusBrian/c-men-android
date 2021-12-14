package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository

class CollectSocketMessagesUseCase(
    private val repository: ChatRepository
) {
    operator fun invoke() = repository.observeMessages()
}