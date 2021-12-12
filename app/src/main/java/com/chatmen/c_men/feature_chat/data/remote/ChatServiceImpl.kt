package com.chatmen.c_men.feature_chat.data.remote

import com.chatmen.c_men.feature_chat.data.remote.dto.ChatDto
import io.ktor.client.*
import io.ktor.client.request.*

class ChatServiceImpl(
    private val client: HttpClient
) : ChatService {

    override suspend fun getAllChats(): List<ChatDto> {
        return client.get(ChatService.Endpoints.AllChats.route)
    }
}