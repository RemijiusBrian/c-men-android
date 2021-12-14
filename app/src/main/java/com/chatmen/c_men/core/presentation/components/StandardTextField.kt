package com.chatmen.c_men.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.chatmen.c_men.core.presentation.ui.theme.PaddingMedium
import com.chatmen.c_men.core.presentation.ui.theme.PaddingSmall
import com.chatmen.c_men.core.presentation.ui.theme.SpaceSmall

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: Int? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    backgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.16f)
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .clip(shape)
            .background(backgroundColor),
        maxLines = maxLines,
        singleLine = singleLine,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface
        )
    ) { innerTextField ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = PaddingMedium, vertical = PaddingSmall)
        ) {
            leadingIcon?.let { leadingIcon ->
                leadingIcon()
                Spacer(modifier = Modifier.width(SpaceSmall))
            }
            Box {
                if (value.isEmpty()) {
                    hint?.let {
                        Text(
                            text = stringResource(id = it),
                            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                        )
                    }
                } else {
                    innerTextField()
                }
            }
            trailingIcon?.let { trailingIcon ->
                Spacer(modifier = Modifier.width(SpaceSmall))
                trailingIcon()
            }
        }
    }
}