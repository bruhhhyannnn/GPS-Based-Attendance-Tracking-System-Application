package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySignupBinding
import com.example.capstone.databinding.ActivitySignupPasswordBinding

class SignupPasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.passwordContinueButton.setOnClickListener {
            startActivity(Intent(this, SignupPasswordActivity::class.java))
        }
    }
}