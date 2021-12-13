package com.chatmen.c_men.feature_members.domain.use_case

import com.chatmen.c_men.feature_members.domain.model.Member

class GroupMembersUseCase {
    operator fun invoke(members: List<Member>?): Map<String, List<Member>> =
        members?.groupBy { it.name[0].uppercase() } ?: emptyMap()
}