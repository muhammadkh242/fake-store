package com.example.fakestore.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fakestore.ui.states.BaseUIState
import com.example.fakestore.ui.viewModels.UserViewModel

@Composable
fun SplashScreen(
    viewModel: UserViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    val isLoggedInState by viewModel.isLoggedIn.collectAsState()
    LaunchedEffect(isLoggedInState) {
        when (isLoggedInState) {
            is BaseUIState.Initial -> {}
            is BaseUIState.Loading -> {}
            is BaseUIState.Error -> navigateToLogin()
            is BaseUIState.Success -> {
                val isLoggedIn = (isLoggedInState as BaseUIState.Success<Boolean>).data
                if (isLoggedIn) {
                    navigateToHome()
                } else {
                    navigateToLogin()
                }

            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Fake Store")
    }
}