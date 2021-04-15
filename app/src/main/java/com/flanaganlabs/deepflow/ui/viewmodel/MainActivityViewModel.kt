package com.flanaganlabs.deepflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.flanaganlabs.deepflow.data.remote.ApiHelper
import com.flanaganlabs.deepflow.data.remote.Resource
import com.flanaganlabs.deepflow.data.remote.RetrofitBuilder
import com.flanaganlabs.deepflow.data.repo.ApiRepository
import kotlinx.coroutines.Dispatchers

class MainActivityViewModel: ViewModel() {

    private val apiRepository by lazy { ApiRepository(ApiHelper(RetrofitBuilder.apiService)) }

    fun getAllPosts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getAllPosts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}