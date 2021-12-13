package com.chatmen.c_men.feature_members.domain.repository

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.feature_members.domain.model.Member
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    fun getAllMembers(refresh: Boolean): Flow<Resource<List<Member>>>
}