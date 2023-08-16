package com.angelina.wallet_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.angelina.wallet_application.screen.BottomBarMainScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    isFirstOpen: Boolean,
    isUserLogin: Boolean
) {

    val startDestination = if (!isUserLogin) Graph.AUTHENTICATION else Graph.HOME

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startDestination
    ) {
        authNavGraph(navController = navController, isFirstOpen)
        composable(route = Graph.HOME) {
            BottomBarMainScreen()
        }
    }
}

object Graph {
    const val ROOT = "ROOT_GRAPH"
    const val AUTHENTICATION = "AUTH_GRAPH"
    const val HOME = "HOME_GRAPH"
}