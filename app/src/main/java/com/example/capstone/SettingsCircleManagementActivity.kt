package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsCircleManagementBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class SettingsCircleManagementActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsCircleManagementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsCircleManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, MainSettingsActivity::class.java))
            finish()
        }

        binding.viewCicleMembers.setOnClickListener {
//            TODO GOTO VIEW CIRCLE MEMBERS
//            startActivity(Intent(this, MainActivity::class.java))

        }
        binding.inviteCode.setOnClickListener {
            startActivity(Intent(this, SettingsCircleManagementCircleCodeActivity::class.java))
        }
        binding.leaveCircle.setOnClickListener {
//            TODO CODE FOR LEAVING CIRCLE
        }
    }
}