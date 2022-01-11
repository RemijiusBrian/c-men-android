package com.chatmen.c_men.feature_auth.domain.use_case

import android.content.SharedPreferences
import com.chatmen.c_men.R
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.core.data.util.SimpleResponse
import com.chatmen.c_men.core.presentation.util.UiText
import com.chatmen.c_men.core.util.Constants

class CheckSavedTokenUseCase(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): SimpleResponse =
        if (sharedPreferences.getString(Constants.JWT_TOKEN_KEY, "").isNullOrEmpty())
            Response.Error(UiText.StringResource(R.string.error_not_logged_in))
        else Response.Success(Unit)
}