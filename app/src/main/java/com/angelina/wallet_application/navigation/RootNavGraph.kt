package com.angelina.wallet_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "ROOT_GRAPH"
    const val AUTHENTICATION = "AUTH_GRAPH"
    const val HOME = "HOME_GRAPH"
}