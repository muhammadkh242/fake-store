package com.example.fakestore.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.UserData
import com.example.fakestore.data.repository.StoreRepository
import com.example.fakestore.ui.states.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    ViewModel() {
    val userState: StateFlow<BaseUIState<UserData?>> =
        storeRepository.getUserDataFlow().map { result ->
            result.fold(
                onSuccess = { BaseUIState.Success(it) },
                onFailure = { BaseUIState.Error("Failed to load user: ${it.message}") }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BaseUIState.Loading
        )

    val isLoggedIn: StateFlow<BaseUIState<Boolean>> = userState.map { state ->
        when (state) {
            is BaseUIState.Initial -> BaseUIState.Initial
            is BaseUIState.Loading -> BaseUIState.Loading
            is BaseUIState.Error -> BaseUIState.Error(state.message)
            is BaseUIState.Success -> {
                val isLoggedIn = state.data != null
                BaseUIState.Success(isLoggedIn)
            }

        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = BaseUIState.Loading
    )
}