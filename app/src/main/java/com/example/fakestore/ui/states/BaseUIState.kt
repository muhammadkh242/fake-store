package com.example.fakestore.ui.states

sealed class BaseUIState<out T> {
    data object Initial : BaseUIState<Nothing>()
    data object Loading : BaseUIState<Nothing>()
    data class Success<T>(val data: T) : BaseUIState<T>()
    data class Error(val message: String) : BaseUIState<Nothing>()
}