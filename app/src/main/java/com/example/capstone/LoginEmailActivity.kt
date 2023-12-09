package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.capstone.databinding.ActivityLoginEmailBinding

class LoginEmailActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailContinueButton.setOnClickListener {
            login()
        }
    }
    fun login() {
        val email = binding.emailInput.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInput.setError("Email not valid")
            return
        }

        val intent = Intent(this, LoginPasswordActivity::class.java)
        intent.putExtra("email_input", email)
        startActivity(intent)
    }
}