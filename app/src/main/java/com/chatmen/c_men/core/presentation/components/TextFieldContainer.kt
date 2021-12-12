package com.chatmen.c_men.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chatmen.c_men.core.presentation.ui.theme.PaddingMedium

@Composable
fun TextFieldContainer(
    error: String,
    textField: @Composable () -> Unit
) {
    Column {
        textField()
        AnimatedVisibility(visible = error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .padding(start = PaddingMedium)
            )
        }
    }
}