package com.chatmen.c_men.feature_auth.domain.repository

import com.chatmen.c_men.core.data.util.SimpleResponse

interface AuthRepository {

    suspend fun authenticate(): SimpleResponse

    suspend fun loginUser(username: String, password: String): SimpleResponse
}