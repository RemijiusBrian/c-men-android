package com.chatmen.c_men.feature_chat.domain.repository

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.feature_chat.data.remote.request.CreateChatRequest
import com.chatmen.c_men.feature_chat.domain.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getAllChats(refresh: Boolean): Flow<Resource<List<Chat>>>

    suspend fun updateLastMessageForChat(id: String, lastMessage: String)

    suspend fun createChat(createChatRequest: CreateChatRequest): SimpleResponse

    suspend fun deleteChatById(id: String)

    suspend fun deleteAll()
}