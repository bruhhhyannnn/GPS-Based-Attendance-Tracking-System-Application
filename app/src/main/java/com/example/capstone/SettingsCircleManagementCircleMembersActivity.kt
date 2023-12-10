package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsCircleManagementCircleMembersBinding

class SettingsCircleManagementCircleMembersActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsCircleManagementCircleMembersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsCircleManagementCircleMembersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, SettingsCircleManagementActivity::class.java))
            finish()
        }

//        TODO FOR RECYCLE VIEW


    }
}