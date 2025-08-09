package com.example.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class UserName(
    @SerializedName("firstname")
    val firstName: String?,
    @SerializedName("lastname")
    val lastName: String?,
)
