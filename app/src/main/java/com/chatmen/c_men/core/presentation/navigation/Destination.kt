package com.chatmen.c_men.core.presentation.navigation

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.chatmen.c_men.R

sealed class Destination(
    val route: String,
    @StringRes val titleRes: Int,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    object SplashScreen : Destination(
        route = NavRoutes.SPLASH_SCREEN,
        titleRes = R.string.destination_splash
    )

    object Login : Destination(
        route = NavRoutes.LOGIN,
        titleRes = R.string.destination_login
    )

    object Chats : Destination(
        route = NavRoutes.CHATS,
        titleRes = R.string.destination_chats
    )

    object Messages : Destination(
        route = NavRoutes.MESSAGES,
        titleRes = R.string.destination_messages,
        arguments = listOf(
            navArgument(NavArgs.CHAT_ID) {
                type = NavType.StringType
                nullable = false
                defaultValue = "-1"
            },
            navArgument(NavArgs.CHAT_NAME) {
                type = NavType.StringType
                nullable = false
                defaultValue = "Messages"
            }
        )
    )

    object Members : Destination(
        route = NavRoutes.MEMBERS,
        titleRes = R.string.destination_members
    )

    fun withArgs(vararg args: String): String = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}

private object NavRoutes {
    const val SPLASH_SCREEN = "splashScreen"
    const val LOGIN = "login"
    const val CHATS = "chats"
    const val MESSAGES = "messages"
    const val MEMBERS = "members"
}