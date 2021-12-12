package com.chatmen.c_men.core.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = Destination.Login.route) {
        // Auth Destinations
        loginDestination(navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.loginDestination(navController: NavHostController) {
    composable(Destination.Login.route) {

    }
}