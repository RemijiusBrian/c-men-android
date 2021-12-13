package com.chatmen.c_men.feature_members.data.repository

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.data.util.networkBoundResource
import com.chatmen.c_men.feature_members.data.local.MemberDataSource
import com.chatmen.c_men.feature_members.data.remote.MemberService
import com.chatmen.c_men.feature_members.domain.model.Member
import com.chatmen.c_men.feature_members.domain.model.toMember
import com.chatmen.c_men.feature_members.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MemberRepositoryImpl(
    private val dataSource: MemberDataSource,
    private val service: MemberService
) : MemberRepository {

    override fun getAllMembers(refresh: Boolean): Flow<Resource<List<Member>>> =
        networkBoundResource(
            query = {
                dataSource.getAllMembers().map { entities ->
                    entities.map { it.toMember() }
                }
            },
            fetch = { service.getAllMembers() },
            saveFetchResult = { result ->
                result.forEach { messageDto ->
                    dataSource.insert(
                        username = messageDto.username,
                        name = messageDto.name,
                        bio = messageDto.bio,
                        profilePictureUrl = messageDto.profilePictureUrl
                    )
                }
            },
            shouldFetch = { it.isNullOrEmpty() || refresh }
        )
}