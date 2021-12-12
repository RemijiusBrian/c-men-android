package com.chatmen.c_men.feature_chat.presentation.chats_list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ChatItem(
    name: String,
    iconUrl: String?,
    lastMessage: String?,
    timestamp: String
) {
    Row {
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.h6
            )
        }
    }
}