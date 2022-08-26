package com.mlopez.interviewfullstack

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlopez.interviewfullstack.models.Product
import com.mlopez.interviewfullstack.models.ResponseUser
import com.mlopez.interviewfullstack.models.Users
import com.mlopez.interviewfullstack.repositories.ProductApiRepository
import com.mlopez.interviewfullstack.repositories.UserApiRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel(private val userRepo: UserApiRepository,
private val productRepo: ProductApiRepository): ViewModel() {
    private val TAG = "SharedViewModel"

    private var _users = MutableLiveData(mutableListOf<Users>())
    val users: LiveData<MutableList<Users>> = _users

    private var _products = MutableLiveData(mutableListOf<Product>())
    val products: LiveData<MutableList<Product>> = _products

    private var _showUsersList = MutableLiveData(false)
    val showUsersList: LiveData<Boolean> = _showUsersList

    fun setShowUsersList(value: Boolean) {
        _showUsersList.postValue(value)
    }

    fun addUser(value: Users) {
        _users.value?.add(value)
    }

    fun saveUsers(value: MutableList<Users>) {
        _users.postValue(value)
    }

    fun saveProducts(value: MutableList<Product>) {
        _products.postValue(value)
    }

    fun fetchAllUsers() {
        viewModelScope.launch {
            val call = userRepo.getAllUsers()
            call.enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if(response.isSuccessful) {
                        Log.d(TAG, "Users count: ${response.body()?.user?.count()}")
                        saveUsers(response.body()?.user!!)
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.d(TAG, "GetAllUser request failed")

                }

            })
        }
    }

    fun fetchUserById(id: Int) {
        viewModelScope.launch {
            val call = userRepo.getUser(id)
            call.enqueue(object: Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if(response.isSuccessful) {
                        addUser(response.body()?.user?.first()!!)
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}