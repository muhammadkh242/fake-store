package com.example.fakestore.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.Product
import com.example.fakestore.data.repository.StoreRepository
import com.example.fakestore.ui.states.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    ViewModel() {

    private val _productDetailsState =
        MutableStateFlow<BaseUIState<Product>>(value = BaseUIState.Loading)
    val productDetailsState = _productDetailsState.asStateFlow()

    fun getProductDetails(id: Int) {
        viewModelScope.launch {
            storeRepository.getProductDetails(id).fold(
                onSuccess = {
                    _productDetailsState.value = BaseUIState.Success(data = it)
                    Log.i("ProductDetailsViewModel", "getProductDetails onSuccess: $it")
                },
                onFailure = {
                    _productDetailsState.value = BaseUIState.Error(message = "An Error Occurred")
                    Log.i("ProductDetailsViewModel", "getProductDetails onFailure: $it")

                }
            )
        }
    }

}