package com.chatmen.c_men.feature_auth.presentation.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.core.presentation.navigation.Destination
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.feature_auth.domain.use_case.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    // Events
    private val _events = Channel<BasicUiEvent>()
    val events = _events.receiveAsFlow()

    init {
        authenticateUser()
    }

    // Authenticate User
    private fun authenticateUser() = viewModelScope.launch {
        val destination = when (authenticateUseCase()) {
            is Response.Error -> Destination.Login.route
            is Response.Success -> Destination.Chats.route
        }
        _events.send(UiEvent.Navigate(destination))
    }
}