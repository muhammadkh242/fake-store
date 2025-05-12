package com.example.fakestore.data.datasource.local

import androidx.room.TypeConverter
import com.example.fakestore.data.model.Rating
import com.google.gson.Gson

class RatingTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun ratingToString(rating: Rating): String {
        return gson.toJson(rating)
    }

    @TypeConverter
    fun stringToRating(ratingString: String): Rating {
        return gson.fromJson(ratingString, Rating::class.java)
    }
}