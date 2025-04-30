package com.example.fakestore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fakestore.ui.screens.CartScreen
import com.example.fakestore.ui.screens.HomeScreen
import com.example.fakestore.ui.screens.HomeScreenWithDataPreview
import com.example.fakestore.ui.screens.ProfileScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreenWithDataPreview()
        }
        composable(route = BottomNavItem.Cart.route) {
            CartScreen()
        }
        composable(route = BottomNavItem.Profile.route) {
            ProfileScreen()
        }
    }
}