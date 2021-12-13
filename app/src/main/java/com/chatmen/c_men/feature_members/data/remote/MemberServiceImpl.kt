package com.chatmen.c_men.feature_members.data.remote

import com.chatmen.c_men.feature_members.data.remote.dto.MemberDto
import io.ktor.client.*
import io.ktor.client.request.*

class MemberServiceImpl(
    private val client: HttpClient
) : MemberService {

    override suspend fun getAllMembers(): List<MemberDto> {
        return client.get(MemberService.Endpoints.AllMembers.route)
    }
}