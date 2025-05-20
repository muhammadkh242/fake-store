package com.example.fakestore.data.datasource.remote

import com.example.fakestore.data.model.Product
import com.example.fakestore.data.model.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("/products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): Response<Product>

    @POST("/auth/login")
    suspend fun login(@Body data: Map<String, String>) : Response<UserData>
}