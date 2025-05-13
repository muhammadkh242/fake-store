package com.example.fakestore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
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
        startDestination = AppDestinations.HOME_GRAPH_ROUTE
    ) {
        navigation(
            startDestination = AppDestinations.HOME_SCREEN_ROUTE,
            route = AppDestinations.HOME_GRAPH_ROUTE,
        ) {
            composable(route = AppDestinations.HOME_SCREEN_ROUTE) {
                HomeScreen(
                    onProductClick = { id ->
                        navController.navigate(AppDestinations.productDetailsRoute(id))
                    }
                )
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

        composable(route = AppDestinations.CART_SCREEN_ROUTE) {
            CartScreen()
        }
        composable(route = AppDestinations.PROFILE_SCREEN_ROUTE) {
            ProfileScreen()
        }
        composable(route = AppDestinations.FAVORITE_SCREEN_ROUTE) {
            FavoriteScreen()
        }


    }
}
