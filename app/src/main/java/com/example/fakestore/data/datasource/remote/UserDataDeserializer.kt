package com.example.fakestore.data.datasource.remote

import com.example.fakestore.data.model.UserAddress
import com.example.fakestore.data.model.UserData
import com.example.fakestore.data.model.UserName
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class UserDataDeserializer : JsonDeserializer<UserData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): UserData {
        val jsonObject = json?.asJsonObject
        /// Assigning default values for id & username
        /// because Login Api Doesn't return full User Data just token
        /// That is for education manner only not a real case Apis structure or handling
        val id = jsonObject?.get("id")?.takeIf { !it.isJsonNull }?.asInt ?: 1
        val username = jsonObject?.get("username")?.takeIf { !it.isJsonNull }?.asString ?: "johnd"
        val token = jsonObject?.get("token")?.takeIf { !it.isJsonNull }?.asString

        return UserData(
            id, username, token,
            userAddress = UserAddress(
                city = "kilcoole",
                street = "new road",
                number = 7682,
            ),
            userName = UserName(
                firstName = "john",
                lastName = "doe",
            ),
            email = "john@gmail.com",
            phone = "1-570-236-7033"
        )
    }
}