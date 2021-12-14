package com.chatmen.c_men.feature_chat.presentation.messages.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.chatmen.c_men.core.presentation.ui.theme.PaddingSmall

@Composable
fun OwnMessage(
    modifier: Modifier = Modifier,
    text: String,
    timestamp: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingSmall)
            .padding(top = 2.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Column(
            modifier = modifier
                .widthIn(min = 40.dp, max = 200.dp)
                .clip(MaterialTheme.shapes.small)
                .clickable(onClick = onClick)
                .background(MaterialTheme.colors.primary)
                .padding(PaddingSmall),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = timestamp,
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}