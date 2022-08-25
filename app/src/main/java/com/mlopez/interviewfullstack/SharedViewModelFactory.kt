package com.mlopez.interviewfullstack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mlopez.interviewfullstack.repositories.ProductApiRepository
import com.mlopez.interviewfullstack.repositories.UserApiRepository

class SharedViewModelFactory(private val userRepo: UserApiRepository,
                            private val productRepo: ProductApiRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(userRepo, productRepo) as T
    }
}