package com.chatmen.c_men.core.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.chatmen.c_men.feature_auth.presentation.login.LoginViewModel
import com.chatmen.c_men.feature_auth.presentation.login.ui.Login
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatViewModel
import com.chatmen.c_men.feature_chat.presentation.chats_list.ui.Chats
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = Destination.Login.route) {
        // Auth Destinations
        loginDestination(navController)

        chatsDestination(navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.loginDestination(navController: NavHostController) {
    composable(Destination.Login.route) {
        val viewModel: LoginViewModel = hiltViewModel()

        Login(
            usernameState = viewModel.usernameState.value,
            passwordState = viewModel.passwordState.value,
            onEvent = viewModel::onEvent,
            eventsFlow = viewModel.events,
            navigate = navController::navigate
        )
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.chatsDestination(navController: NavHostController) {
    composable(
        route = Destination.Chats.route,
    ) {
        val viewModel: ChatViewModel = hiltViewModel()

        Chats(
            state = viewModel.state.value,
            onEvent = viewModel::onEvent
        )
    }
}