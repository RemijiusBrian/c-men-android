package com.chatmen.c_men.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.chatmen.c_men.core.presentation.navigation.Navigation
import com.chatmen.c_men.core.presentation.ui.theme.CMenTheme
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepVisibleCondition {
                println("AppDebug: Splash Screen Visible ${viewModel.isLoading.value}")
                viewModel.isLoading.value
            }

            setOnExitAnimationListener {
                println("AppDebug: Splash Screen Dismiss")
                it.remove()
            }
        }

        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberAnimatedNavController()

            LaunchedEffect(Unit) {
                viewModel.events.collectLatest { event ->
                    when (event) {
                        is UiEvent.Navigate -> {
                            navController.navigate(event.destination)
                        }
                    }
                }
            }

            MainActivityContent(
                scaffoldState = scaffoldState,
                navController = navController
            )
        }
    }
}

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun MainActivityContent(
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        CMenTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                Navigation(navController)
            }
        }
    }
}