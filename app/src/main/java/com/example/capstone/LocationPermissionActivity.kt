package com.example.capstone

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.capstone.databinding.ActivityLocationPermissionBinding
import android.Manifest

class LocationPermissionActivity : AppCompatActivity() {

    lateinit var binding : ActivityLocationPermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enableLocationButton.setOnClickListener {
//            ENABLE LOCATION QUERY
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ),
                PackageManager.PERMISSION_GRANTED
            )

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