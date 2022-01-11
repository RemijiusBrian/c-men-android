package com.chatmen.c_men.feature_chat.domain.use_case

import com.chatmen.c_men.feature_auth.domain.use_case.AuthenticateUseCase

data class ChatUseCases(
    val getChats: GetChatsUseCase,
    val joinChat: JoinChatUseCase,
    val disconnectChat: DisconnectChatUseCase,
    val collectSocketMessages: CollectSocketMessagesUseCase,
    val authenticate: AuthenticateUseCase
)