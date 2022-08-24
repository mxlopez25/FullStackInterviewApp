package com.mlopez.interviewfullstack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.mlopez.interviewfullstack.databinding.ActivityMainBinding
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
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        initAPICall()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.userFragment,
                R.id.productFragment
            )
        )

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

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