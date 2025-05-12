package com.example.fakestore.di

import android.content.Context
import androidx.room.Room
import com.example.fakestore.data.datasource.local.AppDatabase
import com.example.fakestore.data.datasource.local.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "ecommerce_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteProductDao(database: AppDatabase): ProductsDao {
        return database.favoriteProductDao()
    }
}