package com.example.fakestore.data.repository

import com.example.fakestore.data.datasource.remote.ApiService
import com.example.fakestore.data.model.CartProduct
import com.example.fakestore.data.model.Product
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val apiService: ApiService,
) {

    private val cachedProducts = mutableMapOf<Int, Product>()

    suspend fun getAllProducts(): Result<List<Product>> {
        try {
            val response = apiService.getAllProducts()
            if (response.isSuccessful) {
                response.body()?.let {
                    cacheProducts(it)
                    return Result.success(it)
                }
            }
            return Result.failure(Exception("Error: ${response.code()} ${response.message()}"))

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun cacheProducts(products: List<Product>) {
        products.forEach { cachedProducts[it.id] = it }
    }

    fun getLocalProduct(id: Int): Product? {
        return cachedProducts[id]
    }

    suspend fun getCartProducts(id: Int): Result<List<CartProduct>> {
        try {
            val response = apiService.getCartProducts(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it.products)
                }
            }
            return Result.failure(Exception("Error: ${response.code()} ${response.message()}"))

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}
