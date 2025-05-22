package com.example.fakestore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.fakestore.ui.screens.CartScreen
import com.example.fakestore.ui.screens.FavoriteScreen
import com.example.fakestore.ui.screens.HomeScreen
import com.example.fakestore.ui.screens.LoginScreen
import com.example.fakestore.ui.screens.ProductDetailsScreen
import com.example.fakestore.ui.screens.ProfileScreen
import com.example.fakestore.ui.screens.SplashScreen
import com.example.fakestore.ui.viewModels.FavoriteViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.SPLASH_SCREEN_ROUTE
    ) {
        navigation(
            startDestination = AppDestinations.HOME_SCREEN_ROUTE,
            route = AppDestinations.HOME_GRAPH_ROUTE,
        ) {

            composable(route = AppDestinations.HOME_SCREEN_ROUTE) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(AppDestinations.HOME_GRAPH_ROUTE)
                }
                val favoriteViewModel = hiltViewModel<FavoriteViewModel>(parentEntry)
                HomeScreen(
                    onProductClick = { id ->
                        navController.navigate(AppDestinations.productDetailsRoute(id))
                    },
                    favoriteViewModel = favoriteViewModel,
                )
            }
            composable(
                route = AppDestinations.PRODUCT_DETAILS_ROUTE,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: 1
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(AppDestinations.HOME_GRAPH_ROUTE)
                }
                val favoriteViewModel = hiltViewModel<FavoriteViewModel>(parentEntry)
                ProductDetailsScreen(
                    productId = productId,
                    onNavigateUp = { navController.navigateUp() },
                    favoriteViewModel = favoriteViewModel,
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
            val parentEntry = remember(it) {
                navController.getBackStackEntry(AppDestinations.HOME_GRAPH_ROUTE)
            }
            val favoriteViewModel = hiltViewModel<FavoriteViewModel>(parentEntry)
            FavoriteScreen(onProductClick = { id ->
                navController.navigate(AppDestinations.productDetailsRoute(id))
            }, favoriteViewModel = favoriteViewModel)
        }
        composable(route = AppDestinations.LOGIN_SCREEN_ROUTE) {
            LoginScreen(navigateToHome = {
                navController.navigate(AppDestinations.HOME_GRAPH_ROUTE) {
                    popUpTo(
                        AppDestinations.SPLASH_SCREEN_ROUTE
                    ) { inclusive = true }
                }
            })
        }
        composable(route = AppDestinations.SPLASH_SCREEN_ROUTE) {
            SplashScreen(
                navigateToLogin = {
                    navController.navigate(AppDestinations.LOGIN_SCREEN_ROUTE) {
                        popUpTo(
                            AppDestinations.SPLASH_SCREEN_ROUTE
                        ) { inclusive = true }
                    }
                },
                navigateToHome = {
                    navController.navigate(AppDestinations.HOME_GRAPH_ROUTE) {
                        popUpTo(
                            AppDestinations.SPLASH_SCREEN_ROUTE
                        ) { inclusive = true }
                    }
                }
            )
        }


    }
}
