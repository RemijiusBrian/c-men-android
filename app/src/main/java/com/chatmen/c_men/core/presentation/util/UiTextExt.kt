package com.chatmen.c_men.core.presentation.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

fun UiText.asString(context: Context): String =
    when (this) {
        is UiText.Dynamic -> message
        is UiText.StringResource -> context.getString(stringRes)
    }

@Composable
fun UiText.asString(): String =
    when (this) {
        is UiText.Dynamic -> message
        is UiText.StringResource -> stringResource(id = stringRes)
    }