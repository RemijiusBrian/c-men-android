package com.chatmen.c_men.feature_members.domain.use_case

import com.chatmen.c_men.feature_chat.domain.model.Chat
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository

class GetChatWithMemberUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(member: String): Chat? =
        repository.getChatWithMember(member)
}