package com.example.fakestore.data.repository

import com.example.fakestore.data.datasource.local.LocalDataSource
import com.example.fakestore.data.datasource.remote.ApiService
import com.example.fakestore.data.model.UserData
import com.example.fakestore.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource,
    @ApplicationScope private val scope: CoroutineScope
) {
    val userDataState: Flow<UserData?> =
        localDataSource.getUserDataFlow().map { it.getOrNull() }.stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null,

            )


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
                    saveUser(it)
                    return Result.success(it)
                }
            }
            return Result.failure(Exception("Error: ${response.code()} ${response.message()}"))

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private suspend fun saveUser(userData: UserData) {
        localDataSource.saveUserData(userData)
    }


}