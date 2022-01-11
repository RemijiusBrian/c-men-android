package com.chatmen.c_men.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatmen.c_men.core.data.util.Response
import com.chatmen.c_men.core.presentation.navigation.Destination
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.feature_auth.domain.use_case.CheckSavedTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    checkSavedTokenUseCase: CheckSavedTokenUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _events = Channel<BasicUiEvent>()
    val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            when (checkSavedTokenUseCase()) {
                is Response.Error -> {
                    _isLoading.value = false
                    _events.send(UiEvent.Navigate(Destination.Login.route))
                }
                is Response.Success -> {
                    _isLoading.value = false
                }
            }
        }
    }
}