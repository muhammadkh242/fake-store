package com.example.fakestore.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductDetailsScreen(productId: Int, onNavigateUp: () -> Unit) {
    Text("$productId")
}