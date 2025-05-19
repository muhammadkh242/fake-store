package com.example.fakestore.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fakestore.ui.components.LoadingView
import com.example.fakestore.ui.components.ProductsList
import com.example.fakestore.ui.states.BaseUIState
import com.example.fakestore.ui.viewModels.FavoriteViewModel
import com.example.fakestore.ui.viewModels.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onProductClick: (Int) -> Unit,

    ) {
    Log.i("HomeScreen", "${favoriteViewModel.hashCode()}")

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
            ProductsList(
                products,
                onProductClick = onProductClick,
                favoriteViewModel = favoriteViewModel,
            )
        }
    }

}