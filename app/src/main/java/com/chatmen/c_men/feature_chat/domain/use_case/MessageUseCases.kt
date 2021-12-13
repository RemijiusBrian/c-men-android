package com.chatmen.c_men.feature_chat.domain.use_case

data class MessageUseCases(
    val getMessages: GetMessagesUseCase,
    val sendMessage: SendMessageUseCase
)
