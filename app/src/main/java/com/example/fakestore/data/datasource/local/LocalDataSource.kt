package com.example.fakestore.data.datasource.local

import com.example.fakestore.data.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val productsDao: ProductsDao) {
    suspend fun insertFavoriteProduct(product: Product) {
        productsDao.insertFavoriteProduct(product)
    }

    suspend fun removeFavoriteProduct(product: Product) {
        productsDao.removeFavoriteProduct(product)
    }

    suspend fun deleteFavoriteById(productId: Int) {
        productsDao.deleteFavoriteById(productId)
    }

    fun getFavoriteProducts(): Flow<List<Product>> {
        return productsDao.getFavoriteProducts()
    }

    fun isProductFavorite(productId: Int): Flow<Boolean> {
        return productsDao.isProductFavorite(productId)
    }
}