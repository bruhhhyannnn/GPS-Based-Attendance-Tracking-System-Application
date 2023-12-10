package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.capstone.databinding.ActivityLoginChangePasswordBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.auth.FirebaseAuth

class LoginChangePasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changePasswordContinueButton.setOnClickListener {
            resetPassword()
        }
    }

    fun setInProgress(inProgress : Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.changePasswordContinueButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.changePasswordContinueButton.visibility = View.VISIBLE
        }
    }

    fun resetPassword() {
        val email = binding.emailInput.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInput.setError("Email not valid")
            return
        }

        resetPasswordWithFirebase(email)
    }

    fun resetPasswordWithFirebase(email : String) {
        setInProgress(true)

        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnSuccessListener {
                UiUtil.showToast(applicationContext, "Reset password link sent to your Email")
                startActivity(Intent(this, LoginEmailActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                binding.emailInput.setError("Email not valid")
                setInProgress(false)
            }

    }
}