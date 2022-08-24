package com.mlopez.interviewfullstack.models

data class Product(
    val id: Int,
    val name: String? = null,
    val sku: String? = null,
    val price: Double = 0.0
)