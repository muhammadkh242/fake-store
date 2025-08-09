package com.example.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("address")
    val userAddress: UserAddress?,
    @SerializedName("uname")
    val userName: UserName,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
)
