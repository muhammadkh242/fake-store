package com.example.fakestore.ui.screens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fakestore.data.model.Product
import com.example.fakestore.ui.components.ProductItem
import com.example.fakestore.ui.theme.FakeStoreTheme
import com.example.fakestore.ui.viewModels.HomeViewModel

@Preview(showBackground = true)
@Composable
fun HomeScreenWithDataPreview() {
    val products = listOf(
        Product(
            id = 1,
            title = "Sample Product 1",
            price = 19.99,
            description = "This is a sample product",
            category = "Electronics",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        ),
        Product(
            id = 2,
            title = "Sample Product 2",
            price = 29.99,
            description = "Another sample product",
            category = "Clothing",
            image = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"
        )
    )

    FakeStoreTheme {
        HomeScreenContent(
            products = products,
        )
    }
}

@Composable
fun HomeScreenContent(
    products: List<Product>,
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
                ProductItem(product = products[index])
            }
        }
    }

}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }

}