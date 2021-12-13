package com.chatmen.c_men.di

import android.content.SharedPreferences
import com.chatmen.c_men.CMenDatabase
import com.chatmen.c_men.core.data.util.dispatcher_provider.DispatcherProvider
import com.chatmen.c_men.feature_chat.data.local.ChatDataSource
import com.chatmen.c_men.feature_chat.data.local.ChatDataSourceImpl
import com.chatmen.c_men.feature_chat.data.remote.ChatService
import com.chatmen.c_men.feature_chat.data.repository.ChatRepositoryImpl
import com.chatmen.c_men.feature_chat.domain.repository.ChatRepository
import com.chatmen.c_men.feature_chat.domain.use_case.*
import com.google.gson.Gson
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
        dispatcherProvider: DispatcherProvider,
        sharedPreferences: SharedPreferences
    ): ChatDataSource = ChatDataSourceImpl(database, dispatcherProvider, sharedPreferences)

    @Singleton
    @Provides
    fun provideChatRepository(
        dataSource: ChatDataSource,
        service: ChatService,
        gson: Gson
    ): ChatRepository = ChatRepositoryImpl(dataSource, service, gson)

    @Singleton
    @Provides
    fun provideChatUseCases(repository: ChatRepository): ChatUseCases = ChatUseCases(
        getChats = GetChatsUseCase(repository)
    )

    @Singleton
    @Provides
    fun provideMessageUseCases(
        repository: ChatRepository,
    ): MessageUseCases = MessageUseCases(
        getMessages = GetMessagesUseCase(repository),
        sendMessage = SendMessageUseCase(repository)
    )
}