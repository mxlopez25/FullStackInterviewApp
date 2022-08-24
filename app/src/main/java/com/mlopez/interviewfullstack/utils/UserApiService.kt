package com.mlopez.interviewfullstack.utils

import com.mlopez.interviewfullstack.models.ResponseModel
import com.mlopez.interviewfullstack.models.ResponseUser
import com.mlopez.interviewfullstack.models.Users
import retrofit2.Call
import retrofit2.http.*

interface UserApiService {
    @GET("User/status")
    fun getStatus(): Call<ResponseModel>

    @GET("User/all")
    fun getAllUsers(): Call<ResponseUser>

    @GET("User/{id}")
    fun getUserById(@Path("id") clientId: Int): Call<ResponseUser>

    @POST("User/create")
    fun createUser(@Body user: Users): Call<ResponseUser>
}