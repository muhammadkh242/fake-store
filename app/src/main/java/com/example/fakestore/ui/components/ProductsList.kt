package com.example.fakestore.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fakestore.data.model.Product
import com.example.fakestore.ui.viewModels.FavoriteViewModel

@Composable
fun ProductsList(
    products: List<Product>,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onProductClick: (Int) -> Unit,
    favoriteViewModel: FavoriteViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),

            ) {
            items(products.count()) { index ->
                ProductItem(
                    product = products[index], onProductClick = onProductClick,
                    favoriteStateFlow = favoriteViewModel.getFavoriteStateFlow(products[index].id),
                    onFavoriteChange = {
                        favoriteViewModel.toggleFavorite(
                            products[index].id, products[index], it
                        )
                    }
                )
            }
        }
    }

}