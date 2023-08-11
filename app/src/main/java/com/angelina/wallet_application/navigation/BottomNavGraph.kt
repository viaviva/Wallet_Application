package com.angelina.wallet_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.angelina.wallet_application.screen.CardScreen
import com.angelina.wallet_application.screen.ListCardsScreen
import com.angelina.wallet_application.ui.component.BottomBarScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.MyCards.route
    ) {

        composable(BottomBarScreen.MyCards.route) {
            ListCardsScreen()
        }
        composable(BottomBarScreen.Catalog.route) {
            CardScreen()
        }
        composable(BottomBarScreen.Profile.route) {
            CardScreen()
        }
    }
}

