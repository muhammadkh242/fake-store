package com.example.fakestore.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomNavItem(
        route = AppDestinations.HOME_GRAPH_ROUTE,
        title = "Home",
        icon = Icons.Default.Home
    )

/*    data object Cart : BottomNavItem(
        route = AppDestinations.CART_SCREEN_ROUTE,
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )*/

    data object Profile : BottomNavItem(
        route = AppDestinations.PROFILE_SCREEN_ROUTE,
        title = "Profile",
        icon = Icons.Default.Person
    )

    data object Favorite : BottomNavItem(
        route = AppDestinations.FAVORITE_SCREEN_ROUTE,
        title = "Favorite",
        icon = Icons.Default.Favorite
    )

}