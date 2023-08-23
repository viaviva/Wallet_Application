package com.angelina.wallet_application.screen

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.angelina.wallet_application.navigation.BottomNavGraph
import com.angelina.wallet_application.ui.component.BottomBarScreen
import com.angelina.wallet_application.ui.theme.Dimens
import com.angelina.wallet_application.ui.theme.Purple40
import com.angelina.wallet_application.ui.theme.textFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalGetImage
@Composable
fun BottomBarMainScreen(
    rootNavController: NavHostController
) {
    val bottomNavController = rememberNavController()

    val screens = listOf(
        BottomBarScreen.MyCards,
        BottomBarScreen.Catalog,
        BottomBarScreen.Profile
    )

    Scaffold(
        bottomBar = {
            BottomBar(
                bottomNavController = bottomNavController,
                screens
            )
        }
    ) {
        BottomNavGraph(bottomNavController, rootNavController)
    }
}

@Composable
fun BottomBar(
    bottomNavController: NavHostController,
    items: List<BottomBarScreen>
) {

    NavigationBar(
        tonalElevation = Dimens.sdp_0,
        containerColor = Color.White,
        modifier = Modifier
            .height(Dimens.sdp_76)
    ) {
        val navStackBackEntry by bottomNavController.currentBackStackEntryAsState()
        val currentDestination = navStackBackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                onClick = {
                    val value = " "
                    val route =
                        if (screen.route == BottomBarScreen.Catalog.route) BottomBarScreen.Catalog.route + "/${value}"
                        else screen.route

                    bottomNavController.navigate(route) {
                        popUpTo(BottomBarScreen.MyCards.route) {
                            inclusive = false
                        }
                    }

                },
                label = {
                    Text(
                        text = stringResource(id = screen.title),
                        fontSize = Dimens.sp_12,
                        fontFamily = textFontFamily
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "icon"
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    indicatorColor = Color.White,
                    unselectedIconColor = Purple40,
                    unselectedTextColor = Purple40
                )
            )
        }

    }

}