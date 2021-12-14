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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chatmen.c_men.core.presentation.ui.theme.PaddingSmall

@Composable
fun RemoteMessage(
    modifier: Modifier = Modifier,
    remoteUsername: String,
    text: String,
    timestamp: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingSmall)
            .padding(top = 2.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = modifier
                .widthIn(min = 40.dp, max = 200.dp)
                .clip(MaterialTheme.shapes.small)
                .clickable(onClick = onClick)
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.24f))
                .padding(PaddingSmall),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = remoteUsername,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = timestamp,
                style = MaterialTheme.typography.overline,
                textAlign = TextAlign.End
            )
        }
    }
}