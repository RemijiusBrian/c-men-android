package com.chatmen.c_men.feature_auth.data.repository

import android.content.SharedPreferences
import com.chatmen.c_men.R
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.core.presentation.util.UiText
import com.chatmen.c_men.core.util.Constants
import com.chatmen.c_men.feature_auth.data.remote.AuthService
import com.chatmen.c_men.feature_auth.data.remote.request.LoginRequest
import com.chatmen.c_men.feature_auth.domain.repository.AuthRepository
import io.ktor.utils.io.errors.*

class AuthRepositoryImpl(
    private val service: AuthService,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override suspend fun authenticate(): SimpleResponse =
        try {
            service.authenticate()
            Response.Success(Unit)
        } catch (t: Throwable) {
            Response.Error(
                t.localizedMessage?.let { UiText.Dynamic(it) }
                    ?: UiText.unknownError()
            )
        }

    override suspend fun loginUser(username: String, password: String): SimpleResponse {
        return try {
            val request = LoginRequest(username, password)
            val response = service.loginUser(request)

            if (response.successful) {
                response.data?.let { authResponse ->
                    sharedPreferences.edit()
                        .putString(Constants.USERNAME_KEY, authResponse.username)
                        .putString(Constants.JWT_TOKEN_KEY, authResponse.token)
                        .apply()
                }
                Response.Success(Unit)
            } else {
                Response.Error(
                    response.message?.let { UiText.Dynamic(it) }
                        ?: UiText.unknownError()
                )
            }
        } catch (e: IOException) {
            Response.Error(UiText.StringResource(R.string.error_server_not_reached))
        } catch (e: Exception) {
            Response.Error(UiText.StringResource(R.string.error_something_went_wrong))
        }
    }
}