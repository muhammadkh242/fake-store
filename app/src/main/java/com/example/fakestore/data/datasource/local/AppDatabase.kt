package com.example.fakestore.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fakestore.data.model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RatingTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteProductDao(): ProductsDao
}