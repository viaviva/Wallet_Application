package com.angelina.wallet_application.navigation

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.angelina.wallet_application.screen.CardScreen
import com.angelina.wallet_application.screen.addCard.AddCardScreen
import com.angelina.wallet_application.screen.listCards.ListCardsScreen
import com.angelina.wallet_application.screen.profile.ProfileScreen
import com.angelina.wallet_application.screen.scanner.ScannerScreen
import com.angelina.wallet_application.ui.component.BottomBarScreen
import kotlinx.coroutines.launch

const val ITEM_SCREEN = "itemScreen"
const val SCANNER_SCREEN = "scannerScreen"

@ExperimentalGetImage
@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.MyCards.route
    ) {

        composable(BottomBarScreen.MyCards.route) {
            ListCardsScreen {
                navController.navigate("$ITEM_SCREEN/${it}")
            }
        }
        composable(
            BottomBarScreen.Catalog.route + "/{barcode}",
            arguments = listOf(navArgument("barcode") {
                type = NavType.StringType
            })
        ) {
            AddCardScreen(
                it.arguments?.getString("barcode") ?: "",
                onScannerButtonClick = {
                    navController.navigate(SCANNER_SCREEN)
                },
                onAddCardButtonClick = {
                    navController.navigate(BottomBarScreen.MyCards.route) {
                        popUpTo(BottomBarScreen.MyCards.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(BottomBarScreen.Profile.route) {
            val coroutineScope = rememberCoroutineScope()

            ProfileScreen(
                onLogOutClick = {
                    coroutineScope.launch {
                        navController.navigate(
                            Graph.AUTHENTICATION
                        )
                    }
                }
            )
        }
        composable(
            "$ITEM_SCREEN/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) {
            CardScreen(
                it.arguments?.getLong("id") ?: 0,
                onBackArrowClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(SCANNER_SCREEN) {
            ScannerScreen(
                onGetBarcode = {
                    navController.navigate(BottomBarScreen.Catalog.route + "/${it}") {
                        popUpTo(BottomBarScreen.MyCards.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }
    }
}

