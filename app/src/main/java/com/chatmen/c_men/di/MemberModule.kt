package com.chatmen.c_men.di

import com.chatmen.c_men.CMenDatabase
import com.chatmen.c_men.core.data.util.dispatcher_provider.DispatcherProvider
import com.chatmen.c_men.feature_members.data.local.MemberDataSource
import com.chatmen.c_men.feature_members.data.local.MemberDataSourceImpl
import com.chatmen.c_men.feature_members.data.remote.MemberService
import com.chatmen.c_men.feature_members.data.repository.MemberRepositoryImpl
import com.chatmen.c_men.feature_members.domain.repository.MemberRepository
import com.chatmen.c_men.feature_members.domain.use_case.GetMembersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemberModule {

    @Singleton
    @Provides
    fun provideMemberDataSource(
        database: CMenDatabase,
        dispatcherProvider: DispatcherProvider
    ): MemberDataSource = MemberDataSourceImpl(database, dispatcherProvider)

    @Singleton
    @Provides
    fun provideMemberService(client: HttpClient): MemberService = MemberService.create(client)

    @Singleton
    @Provides
    fun provideMemberRepository(
        dataSource: MemberDataSource,
        service: MemberService
    ): MemberRepository = MemberRepositoryImpl(dataSource, service)

    @Singleton
    @Provides
    fun provideGetMemberUseCase(repository: MemberRepository): GetMembersUseCase =
        GetMembersUseCase(repository)
}