package com.chatmen.c_men.feature_members.presentation.members_list

import com.chatmen.c_men.feature_members.domain.model.Member

data class MembersState(
    val members: Map<String, List<Member>> = emptyMap(),
    val isLoading: Boolean = false,
)
