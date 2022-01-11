package com.chatmen.c_men.feature_members.domain.use_case

data class MembersUseCases(
    val getMembers: GetMembersUseCase,
    val groupMembers: GroupMembersUseCase,
    val getChatWithMember: GetChatWithMemberUseCase
)