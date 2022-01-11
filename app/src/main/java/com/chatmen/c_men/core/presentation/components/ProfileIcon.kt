package com.chatmen.c_men.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.material.placeholder

@ExperimentalCoilApi
@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    contentDescription: String,
    iconUrl: String? = null,
    painter: ImagePainter = rememberImagePainter(data = iconUrl),
    isLoading: Boolean = false,
    size: Dp = 32.dp
) {
    println("AppDebug: Painter Data - ${painter.request.data}")
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .clip(CircleShape)
            .placeholder(
                visible = painter.state is ImagePainter.State.Loading
            )
    )
}