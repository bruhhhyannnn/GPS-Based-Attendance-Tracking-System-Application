package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityMainNotificationsBinding

class MainNotificationsActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainNotificationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

//        TODO ADD NOTIFICATIONS HERE
    }
}