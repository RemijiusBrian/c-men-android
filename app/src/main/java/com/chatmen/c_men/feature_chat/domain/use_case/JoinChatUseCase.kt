package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository

class JoinChatUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(): SimpleResponse = repository.initSession()
}