package com.chatmen.c_men.feature_members.data.remote

import android.net.Uri
import androidx.core.net.toFile
import com.chatmen.c_men.core.data.remote.QueryParams
import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.c_men.feature_members.data.remote.dto.MemberDto
import com.chatmen.c_men.feature_members.data.remote.dto.ProfileDto
import com.chatmen.c_men.feature_members.domain.model.UpdateProfileData
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class MemberServiceImpl(
    private val client: HttpClient,
    private val gson: Gson
) : MemberService {

    override suspend fun getAllMembers(): List<MemberDto> {
        return client.get(MemberService.Endpoints.AllMembers.route)
    }

    override suspend fun getMemberProfile(username: String): BasicApiResponse<ProfileDto> {
        return client.get(MemberService.Endpoints.Profile.route) {
            parameter(QueryParams.USERNAME, username)
        }
    }

    override suspend fun updateMemberProfile(
        updateProfileData: UpdateProfileData,
        profilePictureUri: Uri?
    ): BasicApiResponse<Unit> = client.put(MemberService.Endpoints.UpdateProfile.route) {
        val profilePicture = profilePictureUri?.toFile()

        body = MultiPartFormDataContent(
            parts = formData {
                profilePicture?.let { profilePic ->
                    append("profile_picture", profilePic.readBytes(), Headers.build {
                        contentType(ContentType.Image.PNG)
                        append(HttpHeaders.ContentDisposition, "filename=${profilePic.name}")
                    })
                }

                append("update_profile_data", gson.toJson(updateProfileData))
            }
        )
    }
}