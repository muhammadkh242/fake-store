package com.example.fakestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fakestore.navigation.AppDestinations
import com.example.fakestore.navigation.AppNavHost
import com.example.fakestore.navigation.BottomNavItem
import com.example.fakestore.ui.theme.FakeStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeStoreTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorite,
        BottomNavItem.Cart,
        BottomNavItem.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = when (currentRoute) {
        AppDestinations.HOME_GRAPH_ROUTE,
        AppDestinations.HOME_SCREEN_ROUTE,
        AppDestinations.CART_SCREEN_ROUTE,
        AppDestinations.FAVORITE_SCREEN_ROUTE,
        AppDestinations.PROFILE_SCREEN_ROUTE -> true

        else -> false
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar)
                NavigationBar {

                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(text = item.title) },
                            selected = if (item == BottomNavItem.Home) {
                                currentRoute == AppDestinations.HOME_GRAPH_ROUTE ||
                                        currentRoute == AppDestinations.HOME_SCREEN_ROUTE ||
                                        currentRoute?.startsWith(
                                            AppDestinations.PRODUCT_DETAILS_ROUTE.split(
                                                '/'
                                            ).first()
                                        ) == true
                            } else {
                                currentRoute == item.route
                            },
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            AppNavHost(navController = navController)
        }
    }
}
