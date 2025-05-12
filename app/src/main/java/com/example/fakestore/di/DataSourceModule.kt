package com.example.fakestore.di

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
    fun provideLocalDataSource(productsDao: ProductsDao): LocalDataSource {
        return LocalDataSource(productsDao)
    }
}