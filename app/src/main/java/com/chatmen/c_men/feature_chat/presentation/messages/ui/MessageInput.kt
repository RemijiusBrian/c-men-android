package com.chatmen.c_men.feature_chat.presentation.messages.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chatmen.c_men.R
import com.chatmen.c_men.core.presentation.ui.theme.CMenTheme
import com.chatmen.c_men.core.presentation.ui.theme.PaddingExtraSmall

@Composable
fun MessageInput(
    modifier: Modifier = Modifier,
    messageInput: String,
    @StringRes placeHolder: Int? = null,
    onMessageInputChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = messageInput,
            onValueChange = onMessageInputChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
            ),
            shape = CircleShape,
            modifier = Modifier
                .heightIn(
                    min = TextFieldDefaults.MinHeight,
                    max = TextFieldDefaults.MinHeight * 3
                )
                .weight(1f)
                .padding(PaddingExtraSmall),
            placeholder = { placeHolder?.let { Text(text = stringResource(id = it)) } }
        )
        AnimatedVisibility(visible = messageInput.isNotEmpty()) {
            IconButton(
                onClick = onSend,
                modifier = Modifier
                    .padding(PaddingExtraSmall)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colors.primary, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = stringResource(id = R.string.content_send_message),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMessageInput() {
    CMenTheme {
        MessageInput(
            messageInput = "Demo Text",
            onMessageInputChange = {},
            onSend = {}
        )
    }
}