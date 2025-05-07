package com.example.fakestore.navigation

object AppDestinations {
    const val HOME_ROUTE = "home"
    const val CART_ROUTE = "cart"
    const val PROFILE_ROUTE = "profile"
    const val Favorite_ROUTE = "Favorite"
    const val PRODUCT_DETAILS_ROUTE = "product_details/{productId}"

    fun productDetailsRoute(productId: Int): String = "product_details/$productId"
}