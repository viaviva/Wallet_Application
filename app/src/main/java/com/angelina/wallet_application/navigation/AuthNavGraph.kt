package com.angelina.wallet_application.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.angelina.wallet_application.screen.AuthorizationScreen
import com.angelina.wallet_application.screen.EntryScreen
import com.angelina.wallet_application.screen.RegistrationScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Entry.route
    ) {

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
            AuthorizationScreen(
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
}

