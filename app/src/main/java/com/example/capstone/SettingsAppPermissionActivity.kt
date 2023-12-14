package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsAppPermissionBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class SettingsAppPermissionActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsAppPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsAppPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, MainSettingsActivity::class.java))
            finish()
        }

        binding.locationSwitch.setOnClickListener {
//            TODO
        }
        binding.notificationSwitch.setOnClickListener {
//            TODO
        }
    }
}