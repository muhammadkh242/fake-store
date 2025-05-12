package com.example.fakestore.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fakestore.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteProduct(product: Product)

    @Delete
    suspend fun removeFavoriteProduct(product: Product)

    @Query("SELECT * FROM favorite_products")
    fun getFavoriteProducts(): Flow<List<Product>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_products WHERE id = :productId)")
    fun isProductFavorite(productId: Int): Flow<Boolean>

    @Query("DELETE FROM favorite_products WHERE id = :productId")
    suspend fun deleteFavoriteById(productId: Int)
}