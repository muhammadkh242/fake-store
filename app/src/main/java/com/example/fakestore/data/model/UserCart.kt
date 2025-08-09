package com.example.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class UserCart(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("products")
    val products: List<CartProduct>
)
