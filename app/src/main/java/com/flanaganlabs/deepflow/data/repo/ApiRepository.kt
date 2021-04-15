package com.flanaganlabs.deepflow.data.repo

import com.flanaganlabs.deepflow.data.remote.ApiHelper

class ApiRepository(private val apiHelper: ApiHelper) {

    suspend fun getAllPosts() = apiHelper.getApiPosts()
}