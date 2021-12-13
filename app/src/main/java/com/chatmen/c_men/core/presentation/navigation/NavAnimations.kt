package com.chatmen.c_men.core.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import kotlin.math.roundToInt

object NavAnimations {

    private const val DEFAULT_DURATION = 500

    fun slideInFromRightWithFadeIn(
        duration: Int = DEFAULT_DURATION
    ): EnterTransition = slideInHorizontally(
        initialOffsetX = { (it * 0.3).roundToInt() },
        animationSpec = tween(durationMillis = duration)
    ) + fadeIn(animationSpec = tween(durationMillis = duration))

    fun slideOutToRightWithFadeOut(
        duration: Int = DEFAULT_DURATION
    ): ExitTransition = slideOutHorizontally(
        targetOffsetX = { (it * 0.3).roundToInt() },
        animationSpec = tween(durationMillis = duration)
    ) + fadeOut(animationSpec = tween(durationMillis = duration))

    fun slideInFromLeftWithFadeIn(
        duration: Int = DEFAULT_DURATION
    ): EnterTransition = slideInHorizontally(
        initialOffsetX = { -(it * 0.3).roundToInt() },
        animationSpec = tween(durationMillis = duration)
    ) + fadeIn(animationSpec = tween(durationMillis = duration))

    fun slideOutToLeftWithFadeOut(
        duration: Int = DEFAULT_DURATION
    ): ExitTransition = slideOutHorizontally(
        targetOffsetX = { -(it * 0.3).roundToInt() },
        animationSpec = tween(durationMillis = duration)
    ) + fadeOut(animationSpec = tween(durationMillis = duration))
}