package com.example.fakestore.data.repository

import com.example.fakestore.data.datasource.local.LocalDataSource
import com.example.fakestore.data.datasource.remote.ApiService
import com.example.fakestore.data.model.Product
import com.example.fakestore.data.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) {
    suspend fun saveUser(userData: UserData) {
        localDataSource.saveUserData(userData)
    }

    suspend fun getUser(): UserData? {
        return localDataSource.getUserData()
    }

    suspend fun login(username: String, password: String): Result<UserData> {
        try {
            val response = apiService.login(
                data = mapOf(
                    "username" to username,
                    "password" to password,
                )
            )
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

    suspend fun getProductDetails(id: Int): Result<Product> {
        try {
            val response = apiService.getProductDetails(id)
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

    suspend fun addProductToFavorites(product: Product) {
        localDataSource.insertFavoriteProduct(product)
    }

    suspend fun removeProductFromFavorites(product: Product) {
        localDataSource.removeFavoriteProduct(product)
    }

    suspend fun removeProductFromFavoritesById(productId: Int) {
        localDataSource.deleteFavoriteById(productId)
    }

    fun getFavoriteProducts(): Flow<List<Product>> {
        return localDataSource.getFavoriteProducts()
    }

    fun isProductFavorite(productId: Int): Flow<Boolean> {
        return localDataSource.isProductFavorite(productId)
    }

}