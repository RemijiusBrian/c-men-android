package com.chatmen.c_men.core.presentation.util

import androidx.annotation.StringRes
import com.chatmen.c_men.R

sealed class UiText {
    data class Dynamic(val message: String) : UiText()
    data class StringResource(@StringRes val stringRes: Int) : UiText()

    companion object {
        fun unknownError(): UiText = StringResource(R.string.error_unknown)
    }
}
