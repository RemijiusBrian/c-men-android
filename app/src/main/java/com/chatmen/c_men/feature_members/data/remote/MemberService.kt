package com.chatmen.c_men.feature_members.data.remote

import android.net.Uri
import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.c_men.core.util.Constants
import com.chatmen.c_men.feature_members.data.remote.dto.MemberDto
import com.chatmen.c_men.feature_members.data.remote.dto.ProfileDto
import com.chatmen.c_men.feature_members.domain.model.UpdateProfileData
import com.google.gson.Gson
import io.ktor.client.*

interface MemberService {

    suspend fun getAllMembers(): List<MemberDto>

    suspend fun getMemberProfile(username: String): BasicApiResponse<ProfileDto>

    suspend fun updateMemberProfile(
        updateProfileData: UpdateProfileData,
        profilePictureUri: Uri?
    ): BasicApiResponse<Unit>

    companion object {
        fun create(client: HttpClient, gson: Gson): MemberService = MemberServiceImpl(client, gson)
    }

    sealed class Endpoints(val route: String) {
        object AllMembers : Endpoints("${Constants.BASE_URL}members")
        object Profile : Endpoints("${Constants.BASE_URL}member/profile")
        object UpdateProfile : Endpoints("${Constants.BASE_URL}member/update")
    }
}