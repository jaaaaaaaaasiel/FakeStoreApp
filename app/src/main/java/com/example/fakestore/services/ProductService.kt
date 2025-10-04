package com.example.fakestore.services

import com.example.fakestore.models.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    suspend fun getAllProduct(): List<ProductModel>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductModel
}