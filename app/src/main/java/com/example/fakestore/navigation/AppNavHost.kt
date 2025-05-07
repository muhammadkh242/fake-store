package com.example.fakestore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.fakestore.ui.screens.CartScreen
import com.example.fakestore.ui.screens.FavoriteScreen
import com.example.fakestore.ui.screens.HomeScreen
import com.example.fakestore.ui.screens.ProductDetailsScreen
import com.example.fakestore.ui.screens.ProfileScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreen(
                onProductClick = { id ->
                    navController.navigate(AppDestinations.productDetailsRoute(id))
                }
            )
        }
        composable(route = BottomNavItem.Cart.route) {
            CartScreen()
        }
        composable(route = BottomNavItem.Profile.route) {
            ProfileScreen()
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen()
        }
        composable(
            route = AppDestinations.PRODUCT_DETAILS_ROUTE,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 1
            ProductDetailsScreen(
                productId = productId,
                onNavigateUp = { navController.navigateUp() }
            )
        }

    }
}