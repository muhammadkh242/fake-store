package com.example.fakestore.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.Product
import com.example.fakestore.data.repository.StoreRepository
import com.example.fakestore.ui.states.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    ViewModel() {
    private val favoritesState = FavoritesState()
    val favoriteProductsState: StateFlow<List<Product>> = favoritesState.favoriteListState

    init {
        Log.i("FavoriteViewModel", ": FavoriteViewModel")
        viewModelScope.launch {
            val savedFavorites = storeRepository.getFavoriteProducts()
            favoritesState.setFavorites(savedFavorites.firstOrNull() ?: emptyList())
        }
    }


    fun getFavoriteStateFlow(productId: Int): StateFlow<Boolean> {
        return favoritesState.getFavoriteStateFlow(productId)
    }

    fun toggleFavorite(productId: Int, product: Product, isFavorite: Boolean) {
        favoritesState.toggleFavorite(productId, product)
        if (isFavorite) {
            viewModelScope.launch {
                storeRepository.removeProductFromFavorites(product)
            }
        } else {
            viewModelScope.launch {
                storeRepository.addProductToFavorites(product)
            }
        }

    }

}