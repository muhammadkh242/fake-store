package com.example.fakestore.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fakestore.ui.components.ProductsList
import com.example.fakestore.ui.viewModels.FavoriteViewModel

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel, onProductClick: (Int) -> Unit) {

    val products by favoriteViewModel.favoriteProductsState.collectAsStateWithLifecycle()
    ProductsList(
        products = products,
        onProductClick = onProductClick,
        favoriteViewModel = favoriteViewModel,
    )
}