package com.example.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class CartProduct(
    @SerializedName("productId")
    val productId: String,
    @SerializedName("quantity")
    val quantity: Int,
)
