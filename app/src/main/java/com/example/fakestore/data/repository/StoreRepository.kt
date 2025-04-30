package com.example.fakestore.data.repository

import com.example.fakestore.data.datasource.remote.ApiService
import com.example.fakestore.data.model.Product
import javax.inject.Inject

class StoreRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllProducts(): Result<List<Product>> {
        try {
            val response = apiService.getAllProducts()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                }
            }
            return Result.failure(Exception("Error: ${response.code()} ${response.message()}"))

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}