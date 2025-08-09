package com.example.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class UserAddress(
    @SerializedName("city")
    val city: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("number")
    val number: Int?,
)
