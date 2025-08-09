package com.example.fakestore.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fakestore.ui.components.LoadingView
import com.example.fakestore.ui.states.BaseUIState
import com.example.fakestore.ui.viewModels.CartViewModel

@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel()) {
    val productsState by viewModel.productsState.collectAsState()
    when (productsState) {
        is BaseUIState.Loading -> {
            LoadingView()

        }

        is BaseUIState.Error -> {
            val errorState = productsState as BaseUIState.Error
            Text("Error")
        }

        is BaseUIState.Success -> {
            val products = (productsState as BaseUIState.Success).data
            Text("$products")
        }

        BaseUIState.Initial -> TODO()

    }

}