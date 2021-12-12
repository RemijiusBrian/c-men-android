package com.chatmen.c_men.feature_chat.domain.repository

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.feature_chat.domain.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getAllChats(refresh: Boolean): Flow<Resource<List<Chat>>>

    suspend fun updateLastMessageForChat(id: String, lastMessage: String)

    suspend fun deleteChatById(id: String)

    suspend fun deleteAll()
}