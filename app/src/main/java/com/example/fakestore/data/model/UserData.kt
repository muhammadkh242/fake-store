package com.example.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("token")
    val token: String?,
)
