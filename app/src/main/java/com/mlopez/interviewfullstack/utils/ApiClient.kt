package com.mlopez.interviewfullstack.utils

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object UserApiClient {
    private lateinit var client: Retrofit

    fun getClient(): Retrofit {
        if(!this::client.isInitialized) {
            client = Retrofit.Builder()
                .baseUrl("https://fullstack-interview.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return client
    }
}