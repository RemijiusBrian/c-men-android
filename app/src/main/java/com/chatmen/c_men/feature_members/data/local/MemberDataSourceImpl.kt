package com.chatmen.c_men.feature_members.data.local

import chatmen.cmen.MemberEntity
import com.chatmen.c_men.CMenDatabase
import com.chatmen.c_men.core.data.util.dispatcher_provider.DispatcherProvider
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MemberDataSourceImpl(
    db: CMenDatabase,
    private val dispatchers: DispatcherProvider
) : MemberDataSource {

    private val queries = db.memberEntityQueries

    override fun getAllMembers(): Flow<List<MemberEntity>> {
        return queries.getAllMembers().asFlow().mapToList()
    }

    override suspend fun insert(
        username: String,
        bio: String?,
        profilePictureUrl: String?
    ) = withContext(dispatchers.io) {
        queries.insert(username, bio, profilePictureUrl)
    }

    override suspend fun deleteAll() = withContext(dispatchers.io) {
        queries.deleteAll()
    }
}