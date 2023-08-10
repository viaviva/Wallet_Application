package com.angelina.wallet_application.ui.component

import com.angelina.wallet_application.R

sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: Int
){
    object MyCards: BottomBarScreen(
        route = "my_cards",
        title = R.string.my_cards,
        icon = R.drawable.main_icon
    )
    object Catalog: BottomBarScreen(
        route = "catalog",
        title = R.string.catalog,
        icon = R.drawable.ic_catalog
    )
    object Profile: BottomBarScreen(
        route = "profile",
        title = R.string.profile,
        icon = R.drawable.ic_settings
    )
}
