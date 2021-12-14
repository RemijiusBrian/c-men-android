package com.chatmen.c_men.feature_chat.presentation.messages.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chatmen.c_men.R
import com.chatmen.c_men.core.presentation.components.StandardTextField
import com.chatmen.c_men.core.presentation.ui.theme.CMenTheme
import com.chatmen.c_men.core.presentation.ui.theme.PaddingMedium
import com.chatmen.c_men.core.presentation.ui.theme.PaddingSmall
import com.chatmen.c_men.core.presentation.ui.theme.SpaceSmall

@Composable
fun MessageInput(
    modifier: Modifier = Modifier,
    messageInput: String,
    @StringRes placeHolder: Int? = null,
    onMessageInputChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = PaddingMedium, vertical = PaddingSmall),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StandardTextField(
            modifier = modifier
                .weight(1f),
            value = messageInput,
            onValueChange = onMessageInputChange,
            hint = placeHolder,
            maxLines = 4,
        )
        Spacer(modifier = Modifier.width(SpaceSmall))
        AnimatedVisibility(visible = messageInput.isNotEmpty()) {
            IconButton(
                onClick = onSend,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colors.primary, shape = CircleShape)
                    .size(32.dp)
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
            messageInput = "",
            onMessageInputChange = {},
            onSend = {}
        )
    }
}