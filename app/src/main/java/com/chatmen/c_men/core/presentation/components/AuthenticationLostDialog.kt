package com.chatmen.c_men.core.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chatmen.c_men.R

@Composable
fun AuthenticationLostDialog(
    onOKClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = onOKClick) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        title = {
            Text(text = stringResource(id = R.string.authentication_lost))
        },
        text = {
            Text(text = stringResource(id = R.string.msg_authentication_lost))
        }
    )
}