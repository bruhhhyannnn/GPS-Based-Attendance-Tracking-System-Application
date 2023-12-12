package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleClassBinding

class CircleClassActivity : AppCompatActivity() {

    lateinit var binding : ActivityCircleClassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCircleClassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
//            AUTH FOR VERIFICATION CODE
            validate()
        }

        binding.skip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.createCircleButton.setOnClickListener {
            startActivity(Intent(this, FacultyCircleDetailsActivity::class.java))
        }
    }

    fun validate() {
//        TODO VALIDATE INVITE CODE AND GENERATE A FUCKING INVITE CODE
//        val inviteCode = binding.inviteCodeInput.text.toString()
//
//        if (inviteCode.isEmpty()) {
//            binding.inviteCodeInput.setError("Invalid invite code")
//            return
//        }

//        AUTHENTICATE CODE HERE:


//            THEN IF YES
        startActivity(Intent(this, StudentCircleDetailsActivity::class.java))
//            IF NO
//        binding.inviteCodeInput.setError("Invalid invite code")
    }
}