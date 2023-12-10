package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityLocationPermissionBinding

class LocationPermissionActivity : AppCompatActivity() {

    lateinit var binding : ActivityLocationPermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enableLocationButton.setOnClickListener {
//            ENABLE LOCATION QUERY
        }

        binding.enableNotificationButton.setOnClickListener {
//            ENABLE LOCATION QUERY
        }

        binding.locationPermissionContinueButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.goToDashboard.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}