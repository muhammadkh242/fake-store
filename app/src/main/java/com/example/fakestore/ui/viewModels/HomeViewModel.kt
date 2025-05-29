package com.example.fakestore.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.Product
import com.example.fakestore.data.repository.ProductsRepository
import com.example.fakestore.ui.states.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) :
    ViewModel() {

    private val _productsState =
        MutableStateFlow<BaseUIState<List<Product>>>(value = BaseUIState.Loading)
    val productsState = _productsState.asStateFlow()

    init {
        getAllProducts()

    }

    private fun getAllProducts() {

        viewModelScope.launch {
            productsRepository.getAllProducts().fold(
                onSuccess = {
                    _productsState.value = BaseUIState.Success(data = it)
                    Log.i("HomeViewModel", "getAllProducts onSuccess: $it")
                },
                onFailure = {
                    _productsState.value = BaseUIState.Error(message = "An Error Occurred")
                    Log.i("HomeViewModel", "getAllProducts onFailure: $it")

                }
            )
        }
    }
}