package com.example.fakestore.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.fakestore.data.model.Product
import com.example.fakestore.data.model.UserData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productsDao: ProductsDao,
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val USER_KEY = stringPreferencesKey("user_data")
    }

    private val gson = Gson()

    suspend fun saveUserData(userData: UserData) {
        val userJson = gson.toJson(userData)
        dataStore.edit { it[USER_KEY] = userJson }
    }

    suspend fun getUserData(): UserData? {
        val preferences = dataStore.data.first()
        val userJson = preferences[USER_KEY] ?: return null
        return try {
            gson.fromJson(userJson, UserData::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun getUserDataFlow(): Flow<Result<UserData?>> {
        return dataStore.data
            .map { preferences ->
                try {
                    val userJson = preferences[USER_KEY]
                    if (userJson == null) {
                        Result.success(null)
                    } else {
                        val userData = gson.fromJson(userJson, UserData::class.java)
                        Result.success(userData)
                    }
                } catch (e: Exception) {
                    Result.failure(e)
                }
            }
            .catch { error ->
                emit(Result.failure(error))
            }
    }

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