package com.chatmen.feature_auth.data.remote

import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.feature_auth.data.remote.reponse.AuthResponse
import com.chatmen.feature_auth.data.remote.request.LoginRequest
import io.ktor.client.*
import io.ktor.client.features.*

class AuthServiceImpl(
    private val client: HttpClient
) : AuthService {

    override suspend fun authenticate() {
        client.get<Unit>()
    }

    override suspend fun loginUser(loginRequest: LoginRequest): BasicApiResponse<AuthResponse> {
        TODO("Not yet implemented")
    }
}