package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySignupBinding
import com.example.capstone.databinding.ActivitySignupPersonalBinding

class SignupPersonalActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupPersonalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.personalContinueButton.setOnClickListener {
            validatePersonalInput()
        }
    }

    fun validatePersonalInput() {
        val firstName = binding.firstNameInput.text.toString()
        val lastName = binding.lastNameInput.text.toString()

        if (lastName.isEmpty()) {
            binding.firstNameInput.setError("Enter a valid name")
            return
        }
        if (lastName.isEmpty()) {
            binding.lastNameInput.setError("Enter a valid name")
            return
        }
        val intent = Intent(this, SignupEmailActivity::class.java)
        intent.putExtra("first_name", firstName)
        intent.putExtra("last_name", lastName)
        startActivity(intent)
    }
}