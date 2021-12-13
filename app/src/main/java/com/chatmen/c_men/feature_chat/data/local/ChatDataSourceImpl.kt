package com.chatmen.c_men.feature_chat.data.local

import android.content.SharedPreferences
import chatmen.cmen.ChatEntitiy
import chatmen.cmen.GetAllMessagesForChat
import com.chatmen.c_men.CMenDatabase
import com.chatmen.c_men.core.data.util.dispatcher_provider.DispatcherProvider
import com.chatmen.c_men.core.util.Constants
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ChatDataSourceImpl(
    db: CMenDatabase,
    private val dispatchers: DispatcherProvider,
    private val sharedPreferences: SharedPreferences
) : ChatDataSource {

    private val chatQueries = db.chatEntityQueries
    private val messageQueries = db.messageEntityQueries

    override fun getAllChats(): Flow<List<ChatEntitiy>> {
        return chatQueries.getAllChats().asFlow().mapToList()
    }

    override suspend fun insertChat(
        id: String,
        name: String,
        description: String?,
        timestamp: Long,
        chatIconUrl: String?,
        lastMessage: String?
    ) = withContext(dispatchers.io) {
        chatQueries.insert(id, name, description, timestamp, chatIconUrl, lastMessage)
    }

    override suspend fun updateLastMessageForChat(lastMessage: String?, id: String) {
        return withContext(dispatchers.io) {
            chatQueries.updateLastMessageForChat(lastMessage, id)
        }
    }

    override suspend fun deleteChatById(id: String) = withContext(dispatchers.io) {
        chatQueries.deleteById(id)
    }

    override suspend fun deleteAllChats() = withContext(dispatchers.io) {
        chatQueries.deleteAll()
    }

    override fun getAllMessagesForChat(chatId: String): Flow<List<GetAllMessagesForChat>> {
        return messageQueries.getAllMessagesForChat(chatId).asFlow().mapToList()
    }

    override suspend fun insertMessage(
        id: String,
        fromUsername: String,
        text: String,
        timestamp: Long,
        chatId: String
    ) = withContext(dispatchers.io) {
        messageQueries.insert(
            id = id,
            fromUsername = fromUsername,
            text = text,
            timestamp = timestamp,
            chatId = chatId,
            isOwnMessage = fromUsername == sharedPreferences.getString(Constants.USERNAME_KEY, "")
        )
    }

    override suspend fun deleteMessageById(id: String) = withContext(dispatchers.io) {
        messageQueries.deleteById(id)
    }

    override suspend fun deleteAllMessages() = withContext(dispatchers.io) {
        messageQueries.deleteAll()
    }
}