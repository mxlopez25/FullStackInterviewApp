package com.mlopez.interviewfullstack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mlopez.interviewfullstack.databinding.ActivityDetailsBinding
import com.mlopez.interviewfullstack.models.ResponseUser
import com.mlopez.interviewfullstack.models.Users
import com.mlopez.interviewfullstack.repositories.ProductApiRepository
import com.mlopez.interviewfullstack.repositories.UserApiRepository
import com.mlopez.interviewfullstack.utils.Constants
import com.mlopez.interviewfullstack.utils.UserApiClient
import com.mlopez.interviewfullstack.utils.UserApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var _user: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _user = Users(
            id = intent?.getIntExtra(Constants.USER_ID_VALUE, 0),
            firstName = intent.getStringExtra(Constants.USER_FIRSTNAME_VALUE),
            lastName = intent.getStringExtra(Constants.USER_LASTNAME_VALUE),
            email = intent.getStringExtra(Constants.USER_EMAIL_VALUE),
            username = intent.getStringExtra(Constants.USER_USERNAME_VALUE)
        )

        binding.etFirstName.setText(_user.firstName)
        binding.etLastName.setText(_user.lastName)
        binding.etEmail.setText(_user.email)
        binding.etUsername.setText(_user.username)

        binding.btnSaveUser.setOnClickListener {
            if(_user.id == null || _user.id == 0) {
                // Save user
                val retrofit = UserApiClient.getClient()
                val apiService = retrofit.create(UserApiService::class.java)
                val createUser: Call<ResponseUser> = apiService.createUser(_user)

                _user.firstName = binding.etFirstName.text.toString()
                _user.lastName = binding.etLastName.text.toString()
                _user.email = binding.etEmail.text.toString()
                _user.username = binding.etUsername.text.toString()
                _user.password = if (binding.etPassword.text.toString() == binding.etConfirmPassword.text.toString())
                    binding.etPassword.text.toString()
                else
                    "No Password"

                createUser.enqueue(object : Callback<ResponseUser> {
                    override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                        if(response.isSuccessful) {
                            Log.d("Response", response.body()!!.user.count().toString())
                            closeActivity()
                        }
                    }

                    override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                        Log.d("FailedResponse", "It failed")
                    }
                })
            } else {
                // Update
                val retrofit = UserApiClient.getClient()
                val apiService = retrofit.create(UserApiService::class.java)
                _user.firstName = binding.etFirstName.text.toString()
                _user.lastName = binding.etLastName.text.toString()
                _user.email = binding.etEmail.text.toString()
                _user.username = binding.etUsername.text.toString()
                _user.password = if (binding.etPassword.text.toString() == binding.etConfirmPassword.text.toString())
                    binding.etPassword.text.toString()
                else
                    "No Password"
                val updateUser: Call<ResponseUser> = apiService.updateUser(_user)
                updateUser.enqueue(object : Callback<ResponseUser> {
                    override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                        if(response.isSuccessful) {
                            Log.d("Response", response.body()!!.user.count().toString())
                            closeActivity()
                        }
                    }

                    override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                        Log.d("FailedResponse", "It failed")
                    }
                })
            }
        }
    }

    private fun closeActivity() {
        finish()
    }
}