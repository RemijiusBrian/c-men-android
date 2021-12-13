package com.chatmen.c_men.feature_members.domain.use_case

import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.domain.util.Refresh
import com.chatmen.c_men.feature_members.domain.model.Member
import com.chatmen.c_men.feature_members.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow

class GetMembersUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke(refresh: Refresh): Flow<Resource<List<Member>>> =
        repository.getAllMembers(refresh == Refresh.FORCE)
}