package com.example.fakestore.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fakestore.ui.components.AccountInformationCard
import com.example.fakestore.ui.components.AddressInfoCard
import com.example.fakestore.ui.components.LoadingView
import com.example.fakestore.ui.components.PersonalInfoCard
import com.example.fakestore.ui.components.ProductDetailsContent
import com.example.fakestore.ui.states.BaseUIState
import com.example.fakestore.ui.viewModels.ProductDetailsViewModel
import com.example.fakestore.ui.viewModels.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getUserProfile(1)
    }
    val userState by viewModel.userDataState.collectAsState()

    when (userState) {
        is BaseUIState.Loading -> {
            LoadingView()
        }

        is BaseUIState.Error -> {
            val errorState = userState as BaseUIState.Error
            Text("Error")
        }

        is BaseUIState.Success -> {
            val userData = (userState as BaseUIState.Success).data
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,

                ) {
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .padding(16.dp)
                ) {

                    Text("Profile Information", style = MaterialTheme.typography.titleLarge)
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    PersonalInfoCard(
                        firstName = userData.userName.firstName ?: "",
                        lastName = userData.userName.lastName ?: "",
                        phone = userData.phone ?: "",
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    AddressInfoCard(
                        street = userData.userAddress?.street ?: "",
                        number = (userData.userAddress?.number ?: "").toString(),
                        city = userData.userAddress?.city ?: ""
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    AccountInformationCard(
                        username = userData.username ?: "",
                        email = userData.email ?: ""
                    )
                }
            }

        }

        BaseUIState.Initial -> TODO()
    }


}