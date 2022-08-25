package com.mlopez.interviewfullstack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlopez.interviewfullstack.models.Product
import com.mlopez.interviewfullstack.models.Users
import com.mlopez.interviewfullstack.repositories.ProductApiRepository
import com.mlopez.interviewfullstack.repositories.UserApiRepository

class SharedViewModel(private val userRepo: UserApiRepository,
private val productRepo: ProductApiRepository): ViewModel() {

    private var _users = MutableLiveData(listOf<Users>())
    val users: LiveData<List<Users>> = _users

    private var _products = MutableLiveData(listOf<Product>())
    val products: LiveData<List<Product>> = _products

    fun saveUsers(value: List<Users>) {
        _users.postValue(value)
    }

    fun saveProducts(value: List<Product>) {
        _products.postValue(value)
    }
}