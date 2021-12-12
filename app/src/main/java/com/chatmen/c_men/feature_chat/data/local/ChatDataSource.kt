package com.chatmen.c_men.feature_chat.data.local

import chatmen.cmen.ChatEntitiy
import kotlinx.coroutines.flow.Flow

interface ChatDataSource {

    fun getAllChats(): Flow<List<ChatEntitiy>>

    suspend fun insert(
        id: String,
        name: String?,
        description: String?,
        timestamp: Long,
        chatIconUrl: String?,
        lastMessage: String?
    )

    suspend fun updateLastMessageForChat(lastMessage: String? = null, id: String)

    suspend fun deleteById(id: String)

    suspend fun deleteAll()
}