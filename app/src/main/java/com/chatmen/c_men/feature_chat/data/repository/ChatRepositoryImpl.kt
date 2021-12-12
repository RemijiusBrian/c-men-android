package com.chatmen.c_men.feature_chat.data.repository

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.data.util.networkBoundResource
import com.chatmen.c_men.feature_chat.data.local.ChatDataSource
import com.chatmen.c_men.feature_chat.data.remote.ChatService
import com.chatmen.c_men.feature_chat.domain.model.Chat
import com.chatmen.c_men.feature_chat.domain.model.toChat
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val dataSource: ChatDataSource,
    private val service: ChatService
) : ChatRepository {

    override fun getAllChats(refresh: Boolean): Flow<Resource<List<Chat>>> = networkBoundResource(
        query = {
            dataSource.getAllChats().map { entities ->
                entities.map { it.toChat() }
            }
        },
        fetch = { service.getAllChats() },
        saveFetchData = { result ->
            result.forEach { chatDto ->
                dataSource.insert(
                    id = chatDto.chatId,
                    name = chatDto.name,
                    description = chatDto.description,
                    timestamp = chatDto.timestamp,
                    chatIconUrl = chatDto.chatIconUrl,
                    lastMessage = chatDto.lastMessage
                )
            }
        },
        shouldFetch = { it.isNullOrEmpty() || refresh }
    )

    override suspend fun updateLastMessageForChat(id: String, lastMessage: String) {
        dataSource.updateLastMessageForChat(lastMessage, id)
    }

    override suspend fun deleteChatById(id: String) {
        dataSource.deleteById(id)
    }

    override suspend fun deleteAll() {
        dataSource.deleteAll()
    }
}