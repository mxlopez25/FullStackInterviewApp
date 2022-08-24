package com.mlopez.interviewfullstack.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ResponseModel(
    @SerializedName("message") val message: String? = null,
    @SerializedName("date") val date: Date? = null
)
