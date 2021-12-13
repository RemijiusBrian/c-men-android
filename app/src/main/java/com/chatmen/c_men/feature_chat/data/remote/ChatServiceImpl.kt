package com.chatmen.c_men.feature_chat.data.remote

import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.c_men.feature_chat.data.remote.dto.ChatDto
import com.chatmen.c_men.feature_chat.data.remote.request.CreateChatRequest
import io.ktor.client.*
import io.ktor.client.request.*

class ChatServiceImpl(
    private val client: HttpClient
) : ChatService {

    override suspend fun getAllChats(): List<ChatDto> {
        return client.get(ChatService.Endpoints.AllChats.route)
    }

    override suspend fun createChat(request: CreateChatRequest): BasicApiResponse<ChatDto> {
        return client.post(ChatService.Endpoints.CreateChat.route) {
            body = request
        }
    }
}