package com.chatmen.c_men.feature_chat.data.remote

import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.c_men.core.util.Constants
import com.chatmen.c_men.feature_chat.data.remote.dto.ChatDto
import com.chatmen.c_men.feature_chat.data.remote.request.CreateChatRequest
import io.ktor.client.*
import io.ktor.http.cio.websocket.*

interface ChatService {

    suspend fun getAllChats(): List<ChatDto>

    suspend fun createChat(request: CreateChatRequest): BasicApiResponse<ChatDto>

    suspend fun getSocketSession(): WebSocketSession

    companion object {
        fun create(client: HttpClient): ChatService = ChatServiceImpl(client)
        const val SOCKET_ENDPOINT = "ws://192.168.1.5:8080/api/chat"
    }

    sealed class Endpoints(val url: String) {
        object AllChats : Endpoints("${Constants.BASE_URL}chats")
        object CreateChat : Endpoints("${Constants.BASE_URL}chat/create")
        object ChatSocket : Endpoints("${SOCKET_ENDPOINT}/web-socket")
    }
}