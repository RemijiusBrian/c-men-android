package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository

class DisconnectChatUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke() {
        repository.closeSession()
    }
}