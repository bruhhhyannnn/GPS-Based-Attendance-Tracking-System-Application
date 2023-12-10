package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsCircleManagementBinding

class SettingsCircleManagementActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsCircleManagementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsCircleManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
        }

        binding.viewCicleMembers.setOnClickListener {
//            GOTO VIEW CIRCLE MEMBERS
//            startActivity(Intent(this, MainActivity::class.java))

        }
        binding.inviteCode.setOnClickListener {
//            GOTO INVITE CODE
//            startActivity(Intent(this, MainActivity::class.java))

        }
        binding.leaveCircle.setOnClickListener {
//            TODO CODE FOR LEAVING CIRCLE

        }
    }
}