package com.mlopez.interviewfullstack.repositories

import com.mlopez.interviewfullstack.utils.ProductApiService
import com.mlopez.interviewfullstack.utils.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://fullstack-interview.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApiClient: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    val productApiClient: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }
}