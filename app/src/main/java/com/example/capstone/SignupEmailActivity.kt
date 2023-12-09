package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySignupBinding

class SignupEmailActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailContinueButton.setOnClickListener {
            startActivity(Intent(this, SignupPasswordActivity::class.java))
        }


    }
}