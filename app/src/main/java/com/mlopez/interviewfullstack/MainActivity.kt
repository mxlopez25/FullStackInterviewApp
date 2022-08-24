package com.mlopez.interviewfullstack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mlopez.interviewfullstack.models.ResponseModel
import com.mlopez.interviewfullstack.models.ResponseProduct
import com.mlopez.interviewfullstack.models.ResponseUser
import com.mlopez.interviewfullstack.models.Users
import com.mlopez.interviewfullstack.utils.ProductApiService
import com.mlopez.interviewfullstack.utils.UserApiClient
import com.mlopez.interviewfullstack.utils.UserApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAPICall()

    }

    private fun initAPICall() {
        val body = Users(
            null,
            "Test 3",
            "Create 2",
            "test3@test.com",
            "t3create2",
            "password")

        val retrofit = UserApiClient.getClient()
        val apiService = retrofit.create(UserApiService::class.java)
        val statusCall: Call<ResponseModel> = apiService.getStatus()
        val usersCall: Call<ResponseUser> = apiService.getAllUsers()
        val getUserCall: Call<ResponseUser> = apiService.getUserById(1)
        val createUser: Call<ResponseUser> = apiService.createUser(body)

        val productService = retrofit.create(ProductApiService::class.java)
        val productCall: Call<ResponseProduct> = productService.getAllProducts()

        productCall.enqueue(object : Callback<ResponseProduct> {
            override fun onResponse(
                call: Call<ResponseProduct>,
                response: Response<ResponseProduct>
            ) {
                if(response.isSuccessful) {
                    Log.d("Response", response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

//        createUser.enqueue(object : Callback<ResponseUser> {
//            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
//                if(response.isSuccessful) {
//                    Log.d("Response", response.body()!!.user.count().toString())
//            }
//        }
//
//            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
//                Log.d("FailedResponse", "It failed")
//            }
//        })


//        getUserCall.enqueue(object: Callback<ResponseUser> {
//            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
//                if(response.isSuccessful) {
//                    Log.d("Response", response.body()!!.user.count().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
//                Log.d("FailedResponse", "It failed")
//                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
//            }
//        })
//        usersCall.enqueue(object : Callback<ResponseUserModel> {
//            override fun onResponse(call: Call<ResponseUserModel>, response: Response<ResponseUserModel>) {
//                if(response.isSuccessful) {
//                    Log.d("Response", response.body()?.users!!.size.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseUserModel>, t: Throwable) {
//                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
//            }
//        })

//        statusCall.enqueue(object : Callback<ResponseModel> {
//            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
//                if(response.isSuccessful) {
//                    Log.d("Response", response.body()?.message!!)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
//                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
//            }
//        })
    }
}