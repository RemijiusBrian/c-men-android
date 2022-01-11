package com.chatmen.c_men.di

import android.content.SharedPreferences
import com.chatmen.c_men.feature_auth.data.remote.AuthService
import com.chatmen.c_men.feature_auth.data.repository.AuthRepositoryImpl
import com.chatmen.c_men.feature_auth.domain.repository.AuthRepository
import com.chatmen.c_men.feature_auth.domain.use_case.CheckSavedTokenUseCase
import com.chatmen.c_men.feature_auth.domain.use_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthService(client: HttpClient): AuthService = AuthService.create(client)

    @Singleton
    @Provides
    fun provideAuthRepository(
        authService: AuthService,
        sharedPreferences: SharedPreferences
    ): AuthRepository = AuthRepositoryImpl(authService, sharedPreferences)

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase = LoginUseCase(repository)

    @Provides
    fun provideCheckSavedTokenUseCase(sharedPreferences: SharedPreferences): CheckSavedTokenUseCase =
        CheckSavedTokenUseCase(sharedPreferences)
}