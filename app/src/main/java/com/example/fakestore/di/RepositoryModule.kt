package com.example.fakestore.di

import com.example.fakestore.data.datasource.local.LocalDataSource
import com.example.fakestore.data.datasource.remote.ApiService
import com.example.fakestore.data.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideStoreRepository(
        apiService: ApiService,
        localDataSource: LocalDataSource
    ): StoreRepository {
        return StoreRepository(apiService, localDataSource)
    }
}