package com.example.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class CartProduct(
    @SerializedName("productId")
    val title: String,
    @SerializedName("quantity")
    val price: Int,
)
