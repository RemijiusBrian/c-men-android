package com.chatmen.c_men.di

import com.chatmen.c_men.CMenDatabase
import com.chatmen.c_men.core.data.util.dispatcher_provider.DispatcherProvider
import com.chatmen.c_men.feature_chat.data.local.ChatDataSource
import com.chatmen.c_men.feature_chat.data.local.ChatDataSourceImpl
import com.chatmen.c_men.feature_chat.data.remote.ChatService
import com.chatmen.c_men.feature_chat.data.repository.ChatRepositoryImpl
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository
import com.chatmen.c_men.feature_chat.domain.use_case.ChatUseCases
import com.chatmen.c_men.feature_chat.domain.use_case.GetChatsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Singleton
    @Provides
    fun provideChatService(client: HttpClient): ChatService = ChatService.create(client)

    @Singleton
    @Provides
    fun provideChatDataSource(
        database: CMenDatabase,
        dispatcherProvider: DispatcherProvider
    ): ChatDataSource = ChatDataSourceImpl(database, dispatcherProvider)

    @Singleton
    @Provides
    fun provideChatRepository(
        dataSource: ChatDataSource,
        service: ChatService
    ): ChatRepository = ChatRepositoryImpl(dataSource, service)

    @Singleton
    @Provides
    fun provideChatUseCases(repository: ChatRepository): ChatUseCases = ChatUseCases(
        getChats = GetChatsUseCase(repository)
    )
}