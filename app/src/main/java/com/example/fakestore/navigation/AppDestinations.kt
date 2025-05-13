package com.example.fakestore.navigation

object AppDestinations {
    const val HOME_GRAPH_ROUTE = "home_graph"

    const val HOME_SCREEN_ROUTE = "home_screen"
    const val CART_SCREEN_ROUTE = "cart_screen"
    const val PROFILE_SCREEN_ROUTE = "profile_screen"
    const val FAVORITE_SCREEN_ROUTE = "favorite_screen"

    const val PRODUCT_DETAILS_ROUTE = "product_details/{productId}"

    fun productDetailsRoute(productId: Int): String = "product_details/$productId"
}