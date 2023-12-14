package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityMainCircleBinding

class MainCircleActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainCircleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCircleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.addJoinCircle.setOnClickListener {
            startActivity(Intent(this, CircleClassActivity::class.java))
        }
    }
}