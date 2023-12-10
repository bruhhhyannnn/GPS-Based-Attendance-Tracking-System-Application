package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsCircleManagementCircleCodeBinding

class SettingsCircleManagementCircleCodeActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsCircleManagementCircleCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsCircleManagementCircleCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, SettingsCircleManagementActivity::class.java))
            finish()
        }

//            TODO CODE FOR HAVING THE INVITE CODE
        binding.codeInput

        binding.shareCodeButton.setOnClickListener {
//            TODO CODE FOR SHARING IT TO IMPLICT INTENT
        }
    }
}