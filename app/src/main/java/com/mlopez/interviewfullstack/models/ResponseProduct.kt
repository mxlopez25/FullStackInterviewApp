package com.mlopez.interviewfullstack.models

import com.google.gson.annotations.SerializedName

data class ResponseProduct(
    @SerializedName("products") val products: MutableList<Product>
)
