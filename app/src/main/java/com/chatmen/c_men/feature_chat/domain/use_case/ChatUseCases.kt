package com.chatmen.c_men.feature_chat.domain.use_case

data class ChatUseCases(
    val getChats: GetChatsUseCase,
    val joinChat: JoinChatUseCase,
    val disconnectChat: DisconnectChatUseCase,
    val collectSocketMessages: CollectSocketMessagesUseCase
)
