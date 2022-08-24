package com.mlopez.interviewfullstack.models

import com.google.gson.annotations.SerializedName

data class Users (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("firstName") val firstName: String? = null,
    @SerializedName("lastName") val lastName: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("password") val password: String? = null
        )