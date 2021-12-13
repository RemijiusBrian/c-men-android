package com.chatmen.c_men.feature_chat.presentation.chat_messages.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun OwnMessageItem(
    text: String
) {
    Text(text = text, style = MaterialTheme.typography.h6)
}