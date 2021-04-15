package com.flanaganlabs.deepflow.data.remote

import com.flanaganlabs.deepflow.data.model.Affirmation
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Affirmation>
}