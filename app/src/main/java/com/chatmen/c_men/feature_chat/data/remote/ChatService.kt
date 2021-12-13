package com.chatmen.c_men.feature_chat.data.remote

import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.c_men.core.util.Constants
import com.chatmen.c_men.feature_chat.data.remote.dto.ChatDto
import com.chatmen.c_men.feature_chat.data.remote.request.CreateChatRequest
import io.ktor.client.*

interface ChatService {

    suspend fun getAllChats(): List<ChatDto>

    suspend fun createChat(request: CreateChatRequest): BasicApiResponse<ChatDto>

    companion object {
        fun create(client: HttpClient): ChatService = ChatServiceImpl(client)
    }

    sealed class Endpoints(val route: String) {
        object AllChats : Endpoints("${Constants.BASE_URL}chats")
        object CreateChat : Endpoints("${Constants.BASE_URL}chat/create")
    }
}