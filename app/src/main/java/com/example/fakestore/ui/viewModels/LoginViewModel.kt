package com.example.fakestore.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.Product
import com.example.fakestore.data.model.UserData
import com.example.fakestore.data.repository.StoreRepository
import com.example.fakestore.ui.states.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    ViewModel() {

    private val _loginState =
        MutableStateFlow<BaseUIState<UserData>>(value = BaseUIState.Initial)
    val loginState = _loginState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = BaseUIState.Loading
            storeRepository.login(username, password).fold(
                onSuccess = {
                    _loginState.value = BaseUIState.Success(data = it)
                    storeRepository.saveUser(it)
                    Log.i("LoginViewModel", "login: ${it.token}")
                },
                onFailure = {
                    _loginState.value = BaseUIState.Error(message = "An Error Occurred")
                    Log.i("LoginViewModel", "login: $it")
                }
            )
        }
    }
}