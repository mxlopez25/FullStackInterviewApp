package com.mlopez.interviewfullstack.models

import com.google.gson.annotations.SerializedName

data class ResponseUser( @SerializedName("users") val user: MutableList<Users> )
