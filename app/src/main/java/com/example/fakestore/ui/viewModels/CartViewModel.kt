package com.example.fakestore.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.CartProduct
import com.example.fakestore.data.repository.ProductsRepository
import com.example.fakestore.data.repository.UserRepository
import com.example.fakestore.ui.states.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _productsState =
        MutableStateFlow<BaseUIState<List<CartProduct>>>(value = BaseUIState.Loading)
    val productsState = _productsState.asStateFlow()

    init {
        getCartProducts()
    }

    private fun getCartProducts() {
        viewModelScope.launch {
            userRepository.userDataState.collect { userData ->
                userData?.id?.let { it ->
                    val products = productsRepository.getCartProducts(it)
                    products.fold(
                        onSuccess = {
                            _productsState.value = BaseUIState.Success(data = it)
                            Log.i("CartViewModel", "getCartProducts onSuccess: $it")

                        },
                        onFailure = {
                            _productsState.value = BaseUIState.Error(message = "An Error Occurred")
                            Log.i("CartViewModel", "getCartProducts onFailure: $it")

                        },
                    )
                }
            }
        }
    }
}