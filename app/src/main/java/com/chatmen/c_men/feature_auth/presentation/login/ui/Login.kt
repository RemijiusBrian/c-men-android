package com.chatmen.c_men.feature_auth.presentation.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.chatmen.c_men.R
import com.chatmen.c_men.core.domain.states.TextInputState
import com.chatmen.c_men.core.domain.util.InputError
import com.chatmen.c_men.core.presentation.components.PasswordField
import com.chatmen.c_men.core.presentation.components.TextInput
import com.chatmen.c_men.core.presentation.ui.theme.SpaceSmall
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.feature_auth.presentation.login.LoginEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Login(
    usernameState: TextInputState,
    passwordState: TextInputState,
    onEvent: (LoginEvent) -> Unit,
    eventsFlow: Flow<BasicUiEvent>,
    navigate: (String) -> Unit
) {
    val events by rememberUpdatedState(newValue = eventsFlow)
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        events.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                }

                is UiEvent.Navigate -> {
                    // navigate(event.destination)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.70f)
        ) {
            TextInput(
                value = usernameState.text,
                onValueChange = { onEvent(LoginEvent.UsernameChange(it)) },
                error = when (usernameState.error) {
                    InputError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                labelRes = R.string.username
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            PasswordField(
                value = passwordState.text,
                onValueChange = { onEvent(LoginEvent.PasswordChange(it)) },
                error = when (usernameState.error) {
                    InputError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                labelRes = R.string.password
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            Button(
                onClick = { onEvent(LoginEvent.Login) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.login))
            }
        }
    }
}