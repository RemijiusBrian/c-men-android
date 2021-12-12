package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.domain.util.Refresh
import com.chatmen.c_men.feature_chat.domain.model.Chat
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class GetChatsUseCase(
    private val repository: ChatRepository
) {
    operator fun invoke(refresh: Refresh): Flow<Resource<List<Chat>>> =
        repository.getAllChats(refresh == Refresh.FORCE)
}