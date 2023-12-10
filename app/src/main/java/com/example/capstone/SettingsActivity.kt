package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.circleManagement.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.appPermission.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.account.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.about.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.logout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}