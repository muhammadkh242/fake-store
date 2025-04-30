package com.example.fakestore.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    ViewModel() {
    init {
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            storeRepository.getAllProducts().fold(
                onSuccess = {
                    Log.i("HomeViewModel", "getAllProducts onSuccess: $it")
                },
                onFailure = {
                    Log.i("HomeViewModel", "getAllProducts onFailure: $it")

                }
            )
        }
    }
}