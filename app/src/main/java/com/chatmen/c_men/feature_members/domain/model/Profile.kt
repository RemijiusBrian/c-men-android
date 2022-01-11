package com.chatmen.c_men.feature_members.domain.model

data class Profile(
    val username: String,
    val bio: String,
    val profilePictureUrl: String?,
    val isOwnProfile: Boolean,
)