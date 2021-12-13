package com.chatmen.c_men.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun TransparentTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int?,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { titleRes?.let { Text(text = stringResource(id = it)) } },
        elevation = 0.dp,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        backgroundColor = Color.Transparent
    )
}