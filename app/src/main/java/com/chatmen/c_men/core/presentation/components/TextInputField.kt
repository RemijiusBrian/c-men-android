package com.chatmen.c_men.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import com.chatmen.c_men.R

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    error: String = "",
    @StringRes labelRes: Int? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    TextFieldContainer(error = error) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { labelRes?.let { label -> Text(text = stringResource(id = label)) } },
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    trailingIcon?.invoke()
                    AnimatedVisibility(visible = error.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = stringResource(id = R.string.content_error)
                        )
                    }
                }
            },
            isError = error.isNotEmpty(),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = modifier,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            shape = MaterialTheme.shapes.small
        )
    }
}

@Composable
fun OutlinedTextInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    error: String = "",
    @StringRes labelRes: Int? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    TextFieldContainer(error = error) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { labelRes?.let { label -> Text(text = stringResource(id = label)) } },
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    trailingIcon?.invoke()
                    AnimatedVisibility(visible = error.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = stringResource(id = R.string.content_error)
                        )
                    }
                }
            },
            isError = error.isNotEmpty(),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = modifier,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            colors = colors
        )
    }
}