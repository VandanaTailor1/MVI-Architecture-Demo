package com.app.mvidemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mvidemo.intents.MainIntent
import com.app.mvidemo.model.RandomDogResponse
import com.app.mvidemo.network.ApiService
import com.app.mvidemo.network.RetrofitViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainViewModel : ViewModel() {

    private val apiService = RetrofitViewModel.retrofit.create(ApiService::class.java)
    private var _randomDogSuccess: MutableLiveData<RandomDogResponse> = MutableLiveData()
     var randomDogSuccess: MutableLiveData<RandomDogResponse> = _randomDogSuccess

    private var _failure: MutableLiveData<String> = MutableLiveData()
     var failure: MutableLiveData<String> = _failure

    fun fireIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.GetRandomDog -> {
                getRandomDog()
            }

        }
    }

    private fun getRandomDog() {
        apiService.getRandomDogs().enqueue(object : Callback<RandomDogResponse> {

            override fun onResponse(
                call: Call<RandomDogResponse>,
                response: Response<RandomDogResponse>
            ) {
                if (response.isSuccessful) {
                    _randomDogSuccess.postValue(response.body())
                } else {
                    _failure.postValue(response.message())
                }
            }

            override fun onFailure(p0: Call<RandomDogResponse>, p1: Throwable) {
                _failure.postValue(p1.message.toString())
            }

        })
    }
}