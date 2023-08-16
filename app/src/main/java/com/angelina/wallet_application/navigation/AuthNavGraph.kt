package com.angelina.wallet_application.navigation

import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.angelina.wallet_application.screen.EntryScreen
import com.angelina.wallet_application.screen.RegistrationScreen
import com.angelina.wallet_application.screen.authorization.AuthorizationScreen
import com.angelina.wallet_application.screen.onboarding.OnBoardingScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    isFirstOpen: Boolean
) {

    val startDestination = if (!isFirstOpen) AuthScreen.Onboarding.route else AuthScreen.Entry.route

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = startDestination
    ) {
        composable(route = AuthScreen.Onboarding.route) {
            OnBoardingScreen(
                onSkipClick = {
                    navController.navigate(AuthScreen.Entry.route)
                }
            )
        }

        composable(route = AuthScreen.Entry.route) {
            EntryScreen(
                onAuthButtonClick = {
                    navController.navigate(AuthScreen.Login.route)
                },
                onRegButtonClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                }
            )
        }

        composable(route = AuthScreen.Login.route) {
            val coroutineScope = rememberCoroutineScope()

            AuthorizationScreen(
                onLogInButtonClick = {
                    coroutineScope.launch {
                        navController.navigate(
                            Graph.HOME
                        )
                    }
                },
                onNoAccountTextClick = {
                    navController.navigate(AuthScreen.SignUp.route) {
                        popUpTo(AuthScreen.Entry.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable(route = AuthScreen.SignUp.route) {
            RegistrationScreen(
                onHaveAnAccountTextClick = {
                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(AuthScreen.Entry.route) {
                            inclusive = false
                        }
                    }
                },
                onRegistrationButtonClick = {
                    navController.navigate(
                        Graph.HOME
                    ) {
                        popUpTo(AuthScreen.Entry.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }

}

sealed class AuthScreen(val route: String) {
    object Entry : AuthScreen(route = "ENTRY")
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")

    object Onboarding : AuthScreen(route = "ONBOARDING")

}

