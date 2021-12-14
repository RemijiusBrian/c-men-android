package com.chatmen.c_men.feature_chat.domain.repository

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.feature_chat.data.remote.request.CreateChatRequest
import com.chatmen.c_men.feature_chat.data.remote.web_socket.WsClientMessage
import com.chatmen.c_men.feature_chat.domain.model.Chat
import com.chatmen.c_men.feature_chat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getAllChats(refresh: Boolean): Flow<Resource<List<Chat>>>

    suspend fun updateLastMessageForChat(id: String, lastMessage: String)

    suspend fun createChat(createChatRequest: CreateChatRequest): SimpleResponse

    suspend fun deleteChatById(id: String)

    suspend fun deleteAll()

    fun getAllMessagesForChat(chatId: String): Flow<List<Message>>

    suspend fun initSession(): SimpleResponse

    suspend fun sendMessage(clientMessage: WsClientMessage)

    fun observeMessages()

    suspend fun closeSession()
}