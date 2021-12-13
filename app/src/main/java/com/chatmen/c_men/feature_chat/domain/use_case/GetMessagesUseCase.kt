package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.domain.util.Refresh
import com.chatmen.c_men.feature_chat.domain.model.Message
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class GetMessagesUseCase(
    private val repository: ChatRepository
) {
    operator fun invoke(
        chatId: String,
        refresh: Refresh
    ): Flow<Resource<List<Message>>> = repository.getAllMessagesForChat(
        chatId = chatId,
        refresh = refresh == Refresh.FORCE,
        page = 1,
        pageSize = 20
    )
}