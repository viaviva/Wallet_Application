package com.angelina.wallet_application.navigation

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.angelina.wallet_application.screen.EntryScreen
import com.angelina.wallet_application.screen.registration.RegistrationScreen
import com.angelina.wallet_application.screen.authorization.AuthorizationScreen
import com.angelina.wallet_application.screen.onboarding.OnBoardingScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@ExperimentalGetImage
@OptIn(ExperimentalPagerApi::class)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    isFirstOpen: Boolean
) {

    val startDestination = if (!isFirstOpen) Auth.Onboarding.route else Auth.Entry.route

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = startDestination
    ) {
        composable(route = Auth.Onboarding.route) {
            OnBoardingScreen(
                onSkipClick = {
                    navController.navigate(Auth.Entry.route)
                }
            )
        }

        composable(route = Auth.Entry.route) {
            EntryScreen(
                onAuthButtonClick = {
                    navController.navigate(Auth.Login.route)
                },
                onRegButtonClick = {
                    navController.navigate(Auth.SignUp.route)
                }
            )
        }

        composable(route = Auth.Login.route) {
            val coroutineScope = rememberCoroutineScope()

            AuthorizationScreen(
                onLogInButtonClick = {
                    coroutineScope.launch {
                        navController.navigate(
                            Graph.HOME
                        ) {
                            popUpTo(Auth.Entry.route) {
                                inclusive = true
                            }
                        }
                    }
                },
                onNoAccountTextClick = {
                    navController.navigate(Auth.SignUp.route) {
                        popUpTo(Auth.Entry.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable(route = Auth.SignUp.route) {
            RegistrationScreen(
                onHaveAnAccountTextClick = {
                    navController.navigate(Auth.Login.route) {
                        popUpTo(Auth.Entry.route) {
                            inclusive = false
                        }
                    }
                },
                onRegistrationButtonClick = {
                    navController.navigate(
                        Graph.HOME
                    ) {
                        popUpTo(Auth.Entry.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }

}

sealed class Auth(val route: String) {
    object Entry : Auth(route = "ENTRY")
    object Login : Auth(route = "LOGIN")
    object SignUp : Auth(route = "SIGN_UP")
    object Onboarding : Auth(route = "ONBOARDING")

}

