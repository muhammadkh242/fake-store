package com.example.fakestore.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fakestore.ui.components.LoadingView
import com.example.fakestore.ui.components.ProductDetailsContent
import com.example.fakestore.ui.states.BaseUIState
import com.example.fakestore.ui.viewModels.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    productId: Int,
    onNavigateUp: () -> Unit
) {

    LaunchedEffect(productId) {
        viewModel.getProductDetails(productId)
    }
    val productDetailsState by viewModel.productDetailsState.collectAsState()

    when (productDetailsState) {
        is BaseUIState.Loading -> {
            LoadingView()
        }

        is BaseUIState.Error -> {
            val errorState = productDetailsState as BaseUIState.Error
            Text("Error")
        }

        is BaseUIState.Success -> {
            val product = (productDetailsState as BaseUIState.Success).data
            ProductDetailsContent(product, onNavigateUp)
        }
    }
}