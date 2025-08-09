package com.example.fakestore.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.UserData
import com.example.fakestore.data.repository.UserRepository
import com.example.fakestore.ui.states.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _userDataState =
        MutableStateFlow<BaseUIState<UserData>>(value = BaseUIState.Loading)
    val userDataState = _userDataState.asStateFlow()

    fun getUserProfile(id: Int) {
        viewModelScope.launch {
            userRepository.getUserProfile(id).fold(
                onSuccess = {
                    _userDataState.value = BaseUIState.Success(data = it)
                    Log.i("ProfileViewModel", "getUserProfile onSuccess: $it")
                },
                onFailure = {
                    _userDataState.value = BaseUIState.Error(message = "An Error Occurred")
                    Log.i("ProfileViewModel", "getUserProfile onFailure: $it")

                }
            )
        }
    }

}