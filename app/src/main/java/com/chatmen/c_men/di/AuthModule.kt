package com.chatmen.c_men.di

import com.chatmen.c_men.feature_auth.data.remote.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideAuthService(client: HttpClient): AuthService = AuthService.create(client)
}