package com.chatmen.c_men.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun LoadingIndicatorButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        modifier = modifier
    ) {
        Crossfade(targetState = isLoading) { loading ->
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(ButtonDefaults.IconSize),
                    color = MaterialTheme.colors.onPrimary
                )
            } else {
                Text(text = stringResource(id = text))
            }
        }
    }
}