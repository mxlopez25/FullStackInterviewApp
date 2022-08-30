package com.mlopez.interviewfullstack.models

import com.google.gson.annotations.SerializedName

data class Users (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("password") var password: String? = null
        )