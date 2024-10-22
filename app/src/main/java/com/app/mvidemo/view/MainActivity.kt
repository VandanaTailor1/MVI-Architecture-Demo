package com.app.mvidemo.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.mvidemo.R
import com.app.mvidemo.databinding.ActivityMainBinding
import com.app.mvidemo.intents.MainIntent
import com.app.mvidemo.viewmodel.MainViewModel
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel= ViewModelProvider(this)[MainViewModel::class.java]
        attachObserver()
        onClickEvent()
    }

    private fun onClickEvent() {
        binding.button.setOnClickListener {
            mainViewModel.fireIntent(MainIntent.GetRandomDog)
            binding.progressss.visibility=View.VISIBLE
        }
    }

    private fun attachObserver() {
        mainViewModel.randomDogSuccess.observe(this) {
            binding.progressss.visibility=View.GONE
             Glide.with(this)
                 .load(it.message)
                 .into(binding.imgDog)

            binding.msg.text=it.status
         }

        mainViewModel.failure.observe(this) {
            binding.progressss.visibility=View.GONE
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }



}