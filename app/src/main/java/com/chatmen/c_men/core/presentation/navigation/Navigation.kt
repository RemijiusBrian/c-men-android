package com.chatmen.c_men.core.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.chatmen.c_men.feature_auth.presentation.login.LoginViewModel
import com.chatmen.c_men.feature_auth.presentation.login.ui.Login
import com.chatmen.c_men.feature_chat.presentation.chats_list.ChatViewModel
import com.chatmen.c_men.feature_chat.presentation.chats_list.ui.Chats
import com.chatmen.c_men.feature_members.presentation.members_list.MembersViewModel
import com.chatmen.c_men.feature_members.presentation.members_list.ui.Members
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = Destination.Login.route) {
        // Auth Destinations
        loginDestination(navController)

        chatsDestination(navController)

        membersDestination(navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.loginDestination(navController: NavHostController) {
    composable(Destination.Login.route) {
        val viewModel: LoginViewModel = hiltViewModel()

        Login(
            isLoading = viewModel.isLoading.value,
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
        exitTransition = { NavAnimations.slideOutToLeftWithFadeOut() },
        popEnterTransition = { NavAnimations.slideInFromLeftWithFadeIn() }
    ) {
        val viewModel: ChatViewModel = hiltViewModel()

        Chats(
            state = viewModel.state.value,
            onEvent = viewModel::onEvent,
            eventsFlow = viewModel.events,
            navigate = navController::navigate
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
private fun NavGraphBuilder.membersDestination(navController: NavHostController) {
    composable(
        route = Destination.Members.route,
        enterTransition = { NavAnimations.slideInFromRightWithFadeIn() },
        popExitTransition = { NavAnimations.slideOutToRightWithFadeOut() }
    ) {
        val viewModel: MembersViewModel = hiltViewModel()

        Members(
            state = viewModel.state.value,
            onEvent = viewModel::onEvent,
            eventsFlow = viewModel.events,
            navigate = navController::navigate
        )
    }
}