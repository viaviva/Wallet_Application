package com.angelina.wallet_application.navigation

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.angelina.wallet_application.screen.BottomBarMainScreen
import com.angelina.wallet_application.screen.splash.SplashScreen

@ExperimentalGetImage
@Composable
fun RootNavGraph(
    navController: NavHostController,
    isFirstOpen: Boolean,
    isUserLogin: Boolean
) {

    val navigate = if (!isUserLogin) Graph.AUTHENTICATION else Graph.HOME

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        composable(route = Graph.SPLASH) {
            SplashScreen() {
                navController.popBackStack()
                navController.navigate(navigate)
            }
        }
        authNavGraph(navController = navController, isFirstOpen)
        composable(route = Graph.HOME) {
            BottomBarMainScreen()
        }
    }

}

object Graph {
    const val SPLASH = "SPLASH"
    const val ROOT = "ROOT"
    const val AUTHENTICATION = "AUTH"
    const val HOME = "HOME"
}