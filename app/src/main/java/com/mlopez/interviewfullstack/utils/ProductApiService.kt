package com.mlopez.interviewfullstack.utils

import com.mlopez.interviewfullstack.models.Product
import com.mlopez.interviewfullstack.models.ResponseProduct
import retrofit2.Call
import retrofit2.http.*

interface ProductApiService {
    @GET("Product/all")
    fun getAllProducts(): Call<ResponseProduct>

    @GET("Product/{id}")
    fun getProductById(@Path("id") productId: Int): Call<ResponseProduct>

    @POST("Product/create")
    fun createProduct(@Body product: Product): Call<ResponseProduct>

    @PUT("Product/update")
    fun updateProduct(@Body product: Product): Call<ResponseProduct>
}