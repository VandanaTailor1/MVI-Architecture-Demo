package com.app.mvidemo.network

import com.app.mvidemo.model.RandomDogResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("random")
    fun getRandomDogs() : Call<RandomDogResponse>

}