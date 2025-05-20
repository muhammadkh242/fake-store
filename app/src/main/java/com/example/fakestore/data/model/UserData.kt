package com.example.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class UserData(
    val username: String?,
    val password: String?,
    @SerializedName("token")
    val token: String?,
)
