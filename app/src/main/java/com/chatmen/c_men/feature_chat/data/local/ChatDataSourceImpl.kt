package com.chatmen.c_men.feature_chat.data.local

import chatmen.cmen.ChatEntitiy
import com.chatmen.c_men.CMenDatabase
import com.chatmen.c_men.core.data.util.dispatcher_provider.DispatcherProvider
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ChatDataSourceImpl(
    db: CMenDatabase,
    private val dispatchers: DispatcherProvider
) : ChatDataSource {

    private val queries = db.chatEntityQueries

    override fun getAllChats(): Flow<List<ChatEntitiy>> {
        return queries.getAllChats().asFlow().mapToList()
    }

    override suspend fun insert(
        id: String,
        name: String,
        description: String?,
        timestamp: Long,
        chatIconUrl: String?,
        lastMessage: String?
    ) = withContext(dispatchers.io) {
        queries.insert(id, name, description, timestamp, chatIconUrl, lastMessage)
    }

    override suspend fun updateLastMessageForChat(lastMessage: String?, id: String) {
        return withContext(dispatchers.io) {
            queries.updateLastMessageForChat(lastMessage, id)
        }
    }

    override suspend fun deleteById(id: String) = withContext(dispatchers.io) {
        queries.deleteById(id)
    }

    override suspend fun deleteAll() = withContext(dispatchers.io) {
        queries.deleteAll()
    }
}