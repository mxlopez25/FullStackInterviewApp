package com.mlopez.interviewfullstack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mlopez.interviewfullstack.databinding.ActivityDetaileProductsBinding
import com.mlopez.interviewfullstack.databinding.ActivityDetailsBinding
import com.mlopez.interviewfullstack.models.Product
import com.mlopez.interviewfullstack.models.ResponseProduct
import com.mlopez.interviewfullstack.models.ResponseUser
import com.mlopez.interviewfullstack.models.Users
import com.mlopez.interviewfullstack.utils.Constants
import com.mlopez.interviewfullstack.utils.ProductApiService
import com.mlopez.interviewfullstack.utils.UserApiClient
import com.mlopez.interviewfullstack.utils.UserApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetaileProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetaileProductsBinding
    private lateinit var _product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetaileProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _product = Product(
            id = intent?.getIntExtra(Constants.PRODUCT_ID_VALUE, 0)!!,
            name = intent.getStringExtra(Constants.PRODUCT_NAME_VALUE),
            sku = intent.getStringExtra(Constants.PRODUCT_SKU_VALUE),
            price = intent.getDoubleExtra(Constants.PRODUCT_PRICE_VALUE, 0.0)
        )

        binding.etProductName.setText(_product.name)
        binding.etProductSku.setText(_product.sku)
        binding.etPrice.setText(_product.price.toString())


        binding.btnSaveProduct.setOnClickListener {
            if(_product.id == null || _product.id == 0) {
                // Save user
                _product.name = binding.etProductName.text.toString()
                _product.sku = binding.etProductSku.text.toString()
                _product.price = 5.1

                val retrofit = UserApiClient.getClient()
                val apiService = retrofit.create(ProductApiService::class.java)
                val createProduct: Call<ResponseProduct> = apiService.createProduct(_product)



                createProduct.enqueue(object : Callback<ResponseProduct> {
                    override fun onResponse(call: Call<ResponseProduct>, response: Response<ResponseProduct>) {
                        if(response.isSuccessful) {
                            Log.d("Response", response.body()!!.products.count().toString())
                            closeActivity()
                        }
                    }

                    override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
                        Log.d("FailedResponse", "It failed")
                    }
                })
            } else {
                // Update
                val retrofit = UserApiClient.getClient()
                val apiService = retrofit.create(ProductApiService::class.java)

                _product.name = binding.etProductName.text.toString()
                _product.sku = binding.etProductSku.text.toString()
                _product.price = binding.etPrice.text.toString().toDouble()

                val updateUser: Call<ResponseProduct> = apiService.updateProduct(_product)
                updateUser.enqueue(object : Callback<ResponseProduct> {
                    override fun onResponse(call: Call<ResponseProduct>, response: Response<ResponseProduct>) {
                        if(response.isSuccessful) {
                            Log.d("Response", response.body()!!.products.count().toString())
                            closeActivity()
                        }
                    }

                    override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
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