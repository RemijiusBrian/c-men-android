package com.chatmen.feature_auth.data.remote

import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.feature_auth.data.remote.reponse.AuthResponse
import com.chatmen.feature_auth.data.remote.request.LoginRequest
import io.ktor.client.*

interface AuthService {

    suspend fun authenticate()

    suspend fun loginUser(loginRequest: LoginRequest): BasicApiResponse<AuthResponse>

    companion object {
        fun create(client: HttpClient): AuthService = AuthServiceImpl(client)
    }
}