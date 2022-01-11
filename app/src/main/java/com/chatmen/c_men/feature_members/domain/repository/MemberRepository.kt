package com.chatmen.c_men.feature_members.domain.repository

import android.net.Uri
import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.feature_members.domain.model.Member
import com.chatmen.c_men.feature_members.domain.model.Profile
import com.chatmen.c_men.feature_members.domain.model.UpdateProfileData
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    fun getAllMembers(refresh: Boolean): Flow<Resource<List<Member>>>

    suspend fun getProfile(username: String): Response<Profile>

    suspend fun updateProfile(
        updateData: UpdateProfileData,
        profilePictureUri: Uri?
    ): Response<Unit>
}