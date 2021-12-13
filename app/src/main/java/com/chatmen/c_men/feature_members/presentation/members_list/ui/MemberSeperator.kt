package com.chatmen.c_men.feature_members.presentation.members_list.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chatmen.c_men.core.presentation.ui.theme.PaddingMedium
import com.chatmen.c_men.core.presentation.ui.theme.PaddingSmall

@Composable
fun MemberSeparator(
    modifier: Modifier = Modifier,
    separator: String
) {
    Text(
        text = separator,
        modifier = modifier
            .padding(horizontal = PaddingMedium, vertical = PaddingSmall)
    )
}