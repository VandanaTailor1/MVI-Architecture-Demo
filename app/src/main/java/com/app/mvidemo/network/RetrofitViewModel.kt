package com.app.mvidemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitViewModel {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/breeds/image/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}