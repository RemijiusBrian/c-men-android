package com.chatmen.c_men.feature_members.presentation.members_list

sealed class MembersEvent {
    object InitState : MembersEvent()
    data class MemberClick(val member: String) : MembersEvent()
    object Refresh : MembersEvent()
}