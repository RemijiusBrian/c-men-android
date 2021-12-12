package com.chatmen.c_men.core.presentation.util

sealed class UiEvent : BasicUiEvent() {
    object NavigateUp : UiEvent()
    data class Navigate(val destination: String) : UiEvent()
    data class ShowToast(val uiText: UiText) : UiEvent()
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
}