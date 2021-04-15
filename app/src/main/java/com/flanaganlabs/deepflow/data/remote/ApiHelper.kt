package com.flanaganlabs.deepflow.data.remote

class ApiHelper(private val apiService: ApiService) {

    suspend fun getApiPosts() = apiService.getPosts()
}