package com.chatmen.c_men.feature_chat.data.repository

import com.chatmen.c_men.R
import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.core.data.util.networkBoundResource
import com.chatmen.c_men.core.presentation.util.UiText
import com.chatmen.c_men.feature_chat.data.local.ChatDataSource
import com.chatmen.c_men.feature_chat.data.remote.ChatService
import com.chatmen.c_men.feature_chat.data.remote.request.CreateChatRequest
import com.chatmen.c_men.feature_chat.data.remote.web_socket.WsClientMessage
import com.chatmen.c_men.feature_chat.data.remote.web_socket.WsServerMessage
import com.chatmen.c_men.feature_chat.domain.model.Chat
import com.chatmen.c_men.feature_chat.domain.model.Message
import com.chatmen.c_men.feature_chat.domain.model.toChat
import com.chatmen.c_men.feature_chat.domain.model.toMessage
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive

class ChatRepositoryImpl(
    private val dataSource: ChatDataSource,
    private val service: ChatService,
    private val gson: Gson,
    @ApplicationContext private val applicationScope: CoroutineScope
) : ChatRepository {

    private var socket: WebSocketSession? = null

    override fun getAllChats(refresh: Boolean): Flow<Resource<List<Chat>>> = networkBoundResource(
        query = {
            dataSource.getAllChats().map { entities ->
                entities.map { it.toChat() }
            }
        },
        fetch = { service.getAllChats() },
        saveFetchResult = { result ->
            result.forEach { chatDto ->
                dataSource.insertChat(
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

    override suspend fun createChat(createChatRequest: CreateChatRequest): SimpleResponse {
        val response = service.createChat(createChatRequest)

        return if (response.successful) {
            response.data?.let { chatDto ->
                dataSource.insertChat(
                    id = chatDto.chatId,
                    name = chatDto.name,
                    description = chatDto.description,
                    timestamp = chatDto.timestamp,
                    chatIconUrl = chatDto.chatIconUrl,
                    lastMessage = chatDto.lastMessage
                )
            }
            Response.Success(Unit)
        } else {
            Response.Error(
                response.message?.let { UiText.Dynamic(it) }
                    ?: UiText.unknownError()
            )
        }
    }

    override suspend fun updateLastMessageForChat(id: String, lastMessage: String) {
        dataSource.updateLastMessageForChat(lastMessage, id)
    }

    override suspend fun deleteChatById(id: String) {
        dataSource.deleteChatById(id)
    }

    override suspend fun deleteAll() {
        dataSource.deleteAllChats()
    }

    override fun getAllMessagesForChat(chatId: String): Flow<List<Message>> =
        dataSource.getAllMessagesForChat(chatId).map { entities ->
            entities.map { it.toMessage() }
        }


    override suspend fun initSession(): SimpleResponse = try {
        socket = service.getSocketSession()

        if (socket?.isActive == true) {
            println("AppDebug: Successfully Connected to Chat")
            Response.Success(Unit)
        } else {
            println("AppDebug: Couldn\'t establish connection")
            Response.Error(UiText.StringResource(R.string.error_connection_not_established))
        }
    } catch (t: Throwable) {
        println("AppDebug: ${t.localizedMessage}")
        Response.Error(
            t.localizedMessage?.let { UiText.Dynamic(it) }
                ?: UiText.unknownError()
        )
    }

    override suspend fun sendMessage(clientMessage: WsClientMessage) {
        try {
            val json = gson.toJson(clientMessage)
            socket?.send(Frame.Text(json))
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    override fun observeMessages() {
        try {
            socket?.incoming
                ?.consumeAsFlow()
                ?.onEach { frame ->
                    when (frame) {
                        is Frame.Text -> {
                            val json = frame.readText()
                            val wsServerMessage = gson.fromJson(json, WsServerMessage::class.java)

                            dataSource.insertMessage(
                                id = wsServerMessage.messageId,
                                fromUsername = wsServerMessage.fromUsername,
                                text = wsServerMessage.text,
                                timestamp = wsServerMessage.timestamp,
                                chatId = wsServerMessage.chatId
                            )

                            dataSource.updateLastMessageForChat(
                                lastMessage = wsServerMessage.text,
                                id = wsServerMessage.chatId
                            )
                        }
                        is Frame.Binary -> {
                            println("AppDebug: Binary Socket Data - ${frame.readBytes()}")
                        }
                        else -> Unit
                    }
                }?.launchIn(applicationScope)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    override suspend fun closeSession() {
        socket?.close()
        println("AppDebug: Disconnected Chat")
    }

    override suspend fun getChatWithMember(member: String): Chat? =
        dataSource.getChatWithMember(member)?.toChat()
}