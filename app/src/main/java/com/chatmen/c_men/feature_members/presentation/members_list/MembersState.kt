package com.chatmen.c_men.feature_members.presentation.members_list

import com.chatmen.c_men.feature_members.domain.model.Member

data class MembersState(
    val members: List<Member> = emptyList(),
    val isLoading: Boolean = false
)
