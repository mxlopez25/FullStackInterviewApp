package com.mlopez.interviewfullstack.repositories

import com.mlopez.interviewfullstack.models.Product
import com.mlopez.interviewfullstack.models.ResponseProduct
import retrofit2.Call

class ProductApiRepository {
    suspend fun getProduct(id: Int): Call<ResponseProduct> {
        return RetrofitInstance.productApiClient.getProductById(id)
    }

    suspend fun createProduct(product: Product): Call<ResponseProduct> {
        return RetrofitInstance.productApiClient.createProduct(product)
    }

    suspend fun getAllProduct(): Call<ResponseProduct> {
        return RetrofitInstance.productApiClient.getAllProducts()
    }

    suspend fun updateProduct(product: Product): Call<ResponseProduct> {
        return RetrofitInstance.productApiClient.updateProduct(product)
    }
}