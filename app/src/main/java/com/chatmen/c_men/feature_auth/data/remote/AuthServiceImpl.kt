package com.chatmen.c_men.feature_auth.data.remote

import com.chatmen.c_men.core.data.remote.dto.response.BasicApiResponse
import com.chatmen.c_men.feature_auth.data.remote.reponse.AuthResponse
import com.chatmen.c_men.feature_auth.data.remote.request.LoginRequest
import io.ktor.client.*
import io.ktor.client.request.*

class AuthServiceImpl(
    private val client: HttpClient
) : AuthService {

    override suspend fun authenticate() {
        client.get<Unit>(AuthService.Endpoints.Authenticate.endpoint)
    }

    override suspend fun loginUser(loginRequest: LoginRequest): BasicApiResponse<AuthResponse> =
        client.post(AuthService.Endpoints.Login.endpoint) {
            body = loginRequest
        }
}