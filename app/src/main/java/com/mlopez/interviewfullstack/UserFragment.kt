package com.mlopez.interviewfullstack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mlopez.interviewfullstack.adapters.UserListAdapter
import com.mlopez.interviewfullstack.databinding.FragmentUserBinding
import com.mlopez.interviewfullstack.repositories.ProductApiRepository
import com.mlopez.interviewfullstack.repositories.UserApiRepository

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var sharedViewModelFactory: SharedViewModelFactory
    private lateinit var userRepo: UserApiRepository
    private lateinit var productRepo: ProductApiRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        userRepo = UserApiRepository()
        productRepo = ProductApiRepository()

        sharedViewModelFactory = SharedViewModelFactory(userRepo, productRepo)

        sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(SharedViewModel::class.java)

        sharedViewModel.users.observe(viewLifecycleOwner) {
            binding.rvUserList.adapter = UserListAdapter(it)
            binding.rvUserList.layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }


}