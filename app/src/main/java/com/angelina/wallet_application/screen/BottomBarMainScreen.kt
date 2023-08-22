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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.angelina.wallet_application.navigation.BottomNavGraph
import com.angelina.wallet_application.ui.component.BottomBarScreen
import com.angelina.wallet_application.ui.theme.Purple40
import com.angelina.wallet_application.ui.theme.textFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalGetImage
@Composable
fun BottomBarMainScreen() {
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
        BottomNavGraph(navController = bottomNavController)
    }
}

@Composable
fun BottomBar(
    bottomNavController: NavHostController,
    items: List<BottomBarScreen>
) {

    NavigationBar(
        tonalElevation = 0.dp,
        containerColor = Color.White,
        modifier = Modifier
            .height(75.dp)
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
                        fontSize = 12.sp,
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