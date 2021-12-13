package com.chatmen.c_men.feature_chat.data.local

import chatmen.cmen.ChatEntitiy
import chatmen.cmen.GetAllMessagesForChat
import kotlinx.coroutines.flow.Flow

interface ChatDataSource {

    fun getAllChats(): Flow<List<ChatEntitiy>>

    suspend fun insertChat(
        id: String,
        name: String,
        description: String?,
        timestamp: Long,
        chatIconUrl: String?,
        lastMessage: String?
    )

    suspend fun updateLastMessageForChat(lastMessage: String? = null, id: String)

    suspend fun deleteChatById(id: String)

    suspend fun deleteAllChats()

    fun getAllMessagesForChat(chatId: String): Flow<List<GetAllMessagesForChat>>

    suspend fun insertMessage(
        id: String,
        fromUsername: String,
        text: String,
        timestamp: Long,
        chatId: String
    )

    suspend fun deleteMessageById(id: String)

    suspend fun deleteAllMessages()
}