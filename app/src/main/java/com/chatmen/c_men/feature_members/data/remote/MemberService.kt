package com.chatmen.c_men.feature_members.data.remote

import com.chatmen.c_men.core.util.Constants
import com.chatmen.c_men.feature_members.data.remote.dto.MemberDto
import io.ktor.client.*

interface MemberService {

    suspend fun getAllMembers(): List<MemberDto>

    companion object {
        fun create(client: HttpClient): MemberService = MemberServiceImpl(client)
    }

    sealed class Endpoints(val route: String) {
        object AllMembers : Endpoints("${Constants.BASE_URL}members")
    }
}