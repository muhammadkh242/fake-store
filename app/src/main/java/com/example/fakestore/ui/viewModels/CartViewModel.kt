package com.example.fakestore.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.repository.ProductsRepository
import com.example.fakestore.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    init {
        getCartProducts()
    }

    private fun getCartProducts() {
        viewModelScope.launch {
            userRepository.userDataState.collect { userData ->
                userData.let {
                    it?.id?.let { it1 -> productsRepository.getCartProducts(it1) }
                }
            }
        }
    }
}