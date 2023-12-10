package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleWelcomeBinding

class CircleWelcomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityCircleWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCircleWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeContinueButton.setOnClickListener {
            startActivity(Intent(this, CircleClassActivity::class.java))
        }
    }
}