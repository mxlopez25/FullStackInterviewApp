package com.mlopez.interviewfullstack.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApiClient: UserApiRepository by lazy {
        retrofit.create(UserApiRepository::class.java)
    }

    val productApiClient: ProductApiRepository by lazy {
        retrofit.create(ProductApiRepository::class.java)
    }
}