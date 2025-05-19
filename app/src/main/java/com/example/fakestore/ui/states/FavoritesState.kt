package com.example.fakestore.ui.states

import com.example.fakestore.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesState {
    private val _favoriteMap = mutableMapOf<Int, Product>()
    private val _favoriteStateFlows = mutableMapOf<Int, MutableStateFlow<Boolean>>()

    val favorites: List<Product> get() = _favoriteMap.values.toList()

    fun getFavoriteStateFlow(productId: Int): StateFlow<Boolean> {
        val result = _favoriteStateFlows.getOrPut(productId) {
            MutableStateFlow(_favoriteMap.containsKey(productId))
        }.asStateFlow()
        return result
    }

    fun toggleFavorite(productId: Int, product: Product) {
        if (_favoriteMap.containsKey(productId)) {
            removeFromFavorite(productId)
        } else {
            addToFavorite(productId, product)
        }
    }

    private fun addToFavorite(productId: Int, product: Product) {
        _favoriteMap[productId] = product
        updateFavoriteState(productId, true)
    }

    private fun removeFromFavorite(productId: Int) {
        _favoriteMap.remove(productId)
        updateFavoriteState(productId, false)
    }

    fun setFavorites(favorites: List<Product>) {
        val removedIds = _favoriteMap.keys.toSet() - favorites.map { it.id }.toSet()
        _favoriteMap.clear()


        removedIds.forEach {
            updateFavoriteState(it, false)
        }

        favorites.forEach {
            _favoriteMap[it.id] = it
            updateFavoriteState(it.id, true)
        }
    }


    private fun updateFavoriteState(productId: Int, isFavorite: Boolean) {
        val favStateFlow = _favoriteStateFlows[productId]
        if (favStateFlow != null) {
            favStateFlow.value = isFavorite
        }
    }
}