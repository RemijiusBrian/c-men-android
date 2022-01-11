package com.chatmen.c_men.feature_members.data.repository

import android.net.Uri
import com.chatmen.c_men.R
import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.core.data.util.networkBoundResource
import com.chatmen.c_men.core.presentation.util.UiText
import com.chatmen.c_men.feature_members.data.local.MemberDataSource
import com.chatmen.c_men.feature_members.data.remote.MemberService
import com.chatmen.c_men.feature_members.domain.model.Member
import com.chatmen.c_men.feature_members.domain.model.Profile
import com.chatmen.c_men.feature_members.domain.model.UpdateProfileData
import com.chatmen.c_men.feature_members.domain.model.toMember
import com.chatmen.c_men.feature_members.domain.repository.MemberRepository
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MemberRepositoryImpl(
    private val dataSource: MemberDataSource,
    private val service: MemberService
) : MemberRepository {

    override fun getAllMembers(refresh: Boolean): Flow<Resource<List<Member>>> =
        networkBoundResource(
            query = {
                dataSource.getAllMembers().map { entities ->
                    entities.map { it.toMember() }
                }
            },
            fetch = { service.getAllMembers() },
            saveFetchResult = { result ->
                result.forEach { memberDto ->
                    dataSource.insert(
                        username = memberDto.username,
                        bio = memberDto.bio,
                        profilePictureUrl = memberDto.profilePictureUrl
                    )
                }
            },
            shouldFetch = { it.isNullOrEmpty() || refresh }
        )

    override suspend fun getProfile(username: String): Response<Profile> =
        try {
            val result = service.getMemberProfile(username)

            if (result.successful) {
                Response.Success(result.data?.toProfile()!!)
            } else {
                result.message?.let { msg ->
                    Response.Error(UiText.Dynamic(msg))
                } ?: Response.Error(UiText.unknownError())
            }
        } catch (e: IOException) {
            Response.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: Exception) {
            Response.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }

    override suspend fun updateProfile(
        updateData: UpdateProfileData,
        profilePictureUri: Uri?
    ): Response<Unit> = try {
        val result = service.updateMemberProfile(updateData, profilePictureUri)

        if (result.successful) {
            Response.Success(Unit)
        } else {
            result.message?.let { msg ->
                Response.Error(UiText.Dynamic(msg))
            } ?: Response.Error(UiText.unknownError())
        }
    } catch (e: IOException) {
        Response.Error(
            uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
        )
    } catch (e: Exception) {
        Response.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }
}