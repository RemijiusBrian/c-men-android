package com.chatmen.c_men.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.core.domain.states.TextInputState
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.core.presentation.util.UiText
import com.chatmen.c_men.feature_auth.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Loading
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    // Username
    private val _usernameState = mutableStateOf(
        TextInputState(
            text = savedStateHandle.get<String>(SavedStateKeys.USERNAME).orEmpty()
        )
    )
    val usernameState: State<TextInputState> = _usernameState
    private fun onUsernameChange(value: String) {
        savedStateHandle.set(SavedStateKeys.USERNAME, value)
        _usernameState.value = usernameState.value.copy(
            text = value,
        )
        if (usernameState.value.error != null) {
            _usernameState.value = usernameState.value.copy(
                error = null
            )
        }
    }

    // Password
    private val _passwordState = mutableStateOf(
        TextInputState(
            text = savedStateHandle.get<String>(SavedStateKeys.PASSWORD).orEmpty()
        )
    )
    val passwordState: State<TextInputState> = _passwordState
    private fun onPasswordChange(value: String) {
        savedStateHandle.set(SavedStateKeys.PASSWORD, value)
        _passwordState.value = passwordState.value.copy(
            text = value
        )
        if (passwordState.value.error != null) {
            _usernameState.value = usernameState.value.copy(
                error = null
            )
        }
    }

    // Ui Events
    private val _events = Channel<BasicUiEvent>()
    val events = _events.receiveAsFlow()

    // On Event
    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Login -> {
                loginUser(usernameState.value.text, passwordState.value.text)
            }
            is LoginEvent.PasswordChange -> {
                onPasswordChange(event.password)
            }
            is LoginEvent.UsernameChange -> {
                onUsernameChange(event.username)
            }
        }
    }

    // Login User
    private fun loginUser(username: String, password: String) = viewModelScope.launch {
        _isLoading.value = true
        val result = login(username, password)

        result.usernameError?.let { _usernameState.value = usernameState.value.copy(error = it) }
        result.passwordError?.let { _passwordState.value = passwordState.value.copy(error = it) }

        result.response?.let { response ->
            when (response) {
                is Response.Error -> {
                    _events.send(
                        UiEvent.ShowSnackbar(response.message ?: UiText.unknownError())
                    )
                }
                is Response.Success -> {
                    _events.send(UiEvent.NavigateUp)
                }
            }
        }
        _isLoading.value = false
    }

    // Saved State Keys
    private object SavedStateKeys {
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }
}