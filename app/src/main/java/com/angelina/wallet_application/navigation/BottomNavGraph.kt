package com.angelina.wallet_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.angelina.wallet_application.screen.CardScreen
import com.angelina.wallet_application.screen.listCards.ListCardsScreen
import com.angelina.wallet_application.ui.component.BottomBarScreen

const val ITEM_SCREEN = "itemScreen"

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.MyCards.route
    ) {

        composable(BottomBarScreen.MyCards.route) {
            ListCardsScreen() {
                navController.navigate("$ITEM_SCREEN/${it}")
            }
        }
        composable(BottomBarScreen.Catalog.route) {
            ListCardsScreen()
        }
        composable(BottomBarScreen.Profile.route) {
            ListCardsScreen()
        }
        composable(
            "$ITEM_SCREEN/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) {
            CardScreen(it.arguments?.getLong("id") ?: 0)
        }
    }
}

