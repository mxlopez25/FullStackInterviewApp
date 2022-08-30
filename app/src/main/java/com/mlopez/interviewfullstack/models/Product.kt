package com.mlopez.interviewfullstack.models

data class Product(
    var id: Int,
    var name: String? = null,
    var sku: String? = null,
    var price: Double = 0.0
)