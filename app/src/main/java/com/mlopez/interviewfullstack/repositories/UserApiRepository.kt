package com.mlopez.interviewfullstack.repositories

import com.mlopez.interviewfullstack.models.ResponseUser
import com.mlopez.interviewfullstack.models.Users
import retrofit2.Call

class UserApiRepository {
    suspend fun getAllUsers(): Call<ResponseUser> {
        return RetrofitInstance.userApiClient.getAllUsers()
    }

    suspend fun getUser(id: Int): Call<ResponseUser> {
        return RetrofitInstance.userApiClient.getUser(id)
    }

    suspend fun createUser(user: Users): Call<ResponseUser> {
        return RetrofitInstance.userApiClient.createUser(user)
    }
}