package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsAccountChangePasswordBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsAccountChangePasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsAccountChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsAccountChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            validate()
        }
        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(this, LoginChangePasswordActivity::class.java))
        }
    }

    fun validate() {
        val current_password = binding.currentPassword.text.toString()
        val new_password = binding.newPassword.text.toString()
        val confirm_password = binding.confirmPassword.text.toString()

        if (current_password.isEmpty()) {
            binding.currentPassword.setError("Wrong password")
            return
        }
        if (new_password.isEmpty() || new_password.length < 8) {
            binding.newPassword.setError("Enter proper new password")
            return
        }
        if (confirm_password.isEmpty() || confirm_password.length < 8) {
            binding.confirmPassword.setError("Password doesn't match")
            return
        }
        updatePasswordWithFirebase()
    }

    fun updatePasswordWithFirebase() {
//        TODO
    }
}