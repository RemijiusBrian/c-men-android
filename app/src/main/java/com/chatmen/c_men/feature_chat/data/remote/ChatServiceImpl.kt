package com.chatmen.c_men.feature_chat.data.remote

import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.c_men.feature_chat.data.remote.dto.ChatDto
import com.chatmen.c_men.feature_chat.data.remote.request.CreateChatRequest
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*

class ChatServiceImpl(
    private val client: HttpClient
) : ChatService {

    override suspend fun getAllChats(): List<ChatDto> {
        return client.get(ChatService.Endpoints.AllChats.url)
    }

    override suspend fun createChat(request: CreateChatRequest): BasicApiResponse<ChatDto> {
        return client.post(ChatService.Endpoints.CreateChat.url) {
            body = request
        }
    }

    override suspend fun getSocketSession(): WebSocketSession = client.webSocketSession {
        url(ChatService.Endpoints.ChatSocket.url)
    }
}