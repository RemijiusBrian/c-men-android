package com.chatmen.c_men.feature_members.data.local

import chatmen.cmen.MemberEntity
import kotlinx.coroutines.flow.Flow

interface MemberDataSource {

    fun getAllMembers(): Flow<List<MemberEntity>>

    suspend fun insert(
        username: String,
        bio: String?,
        profilePictureUrl: String?
    )

    suspend fun getProfileForMember(username: String): MemberEntity?

    suspend fun deleteAll()
}