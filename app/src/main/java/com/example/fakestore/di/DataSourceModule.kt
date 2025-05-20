package com.example.fakestore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.fakestore.data.datasource.local.LocalDataSource
import com.example.fakestore.data.datasource.local.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        productsDao: ProductsDao,
        dataStore: DataStore<Preferences>
    ): LocalDataSource {
        return LocalDataSource(productsDao, dataStore)
    }


}