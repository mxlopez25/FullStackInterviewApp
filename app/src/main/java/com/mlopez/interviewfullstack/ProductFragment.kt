package com.mlopez.interviewfullstack

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mlopez.interviewfullstack.adapters.ProductListAdapter
import com.mlopez.interviewfullstack.databinding.FragmentProductBinding
import com.mlopez.interviewfullstack.repositories.ProductApiRepository
import com.mlopez.interviewfullstack.repositories.UserApiRepository

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var sharedViewModelFactory: SharedViewModelFactory
    private lateinit var userRepository: UserApiRepository
    private lateinit var productRepository: ProductApiRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)

        userRepository = UserApiRepository()
        productRepository = ProductApiRepository()

        sharedViewModelFactory = SharedViewModelFactory(userRepository, productRepository)
        sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(SharedViewModel::class.java)

        sharedViewModel.products.observe(viewLifecycleOwner) {
            binding.rvProductList.adapter = ProductListAdapter(it)
            binding.rvProductList.layoutManager = LinearLayoutManager(context)
        }

        binding.fabProduct.setOnClickListener {
            val intent = Intent(context, DetaileProductsActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    companion object {

    }
}