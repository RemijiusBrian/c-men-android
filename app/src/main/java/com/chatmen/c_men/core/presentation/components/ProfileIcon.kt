package com.chatmen.c_men.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    iconUrl: String? = null,
    contentDescription: String,
    isLoading: Boolean = false
) {
    Image(
        painter = rememberImagePainter(
            data = iconUrl,
            builder = {
                crossfade(true)
                transformations(CircleCropTransformation())
            },
        ),
        contentDescription = contentDescription,
        modifier = modifier
            .size(32.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface,
                shape = CircleShape
            )
            .placeholder(
                visible = isLoading,
                shape = CircleShape,
                color = Color.Transparent,
                highlight = PlaceholderHighlight.fade()
            )
    )
}