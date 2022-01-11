package com.chatmen.c_men.feature_members.data.remote.dto

import com.chatmen.c_men.feature_members.domain.model.Profile

data class ProfileDto(
    val username: String,
    val bio: String,
    val profilePictureUrl: String,
    val isOwnProfile: Boolean,
) {
    fun toProfile(): Profile = Profile(
        username = username,
        bio = bio,
        profilePictureUrl = profilePictureUrl,
        isOwnProfile = isOwnProfile
    )
}