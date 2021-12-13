package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.feature_chat.data.remote.request.CreateChatRequest
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository

class CreateChatUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(
        name: String? = null,
        members: List<String>,
        description: String?,
        chatIconUrl: String?
    ): SimpleResponse {
        val request = CreateChatRequest(
            name = name,
            members = members,
            description = description,
            chatIconUrl = chatIconUrl
        )
        return repository.createChat(request)
    }
}