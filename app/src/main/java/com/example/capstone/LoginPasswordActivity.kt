package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.capstone.databinding.ActivityLoginPasswordBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.auth.FirebaseAuth

class LoginPasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.passwordContinueButton.setOnClickListener {
            login()
        }
        binding.goToForgotPassword.setOnClickListener {
            startActivity(Intent(this, LoginChangePasswordActivity::class.java))
        }
    }

    fun setInProgress(inProgress : Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.passwordContinueButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.passwordContinueButton.visibility = View.VISIBLE
        }
    }

    fun login() {
        val email = if (intent != null)
            intent.extras?.getString("email_input") ?: "default_email"
        else
            "null_value"
        val password = binding.passwordInput.text.toString()

        if (password.isEmpty()) {
            binding.passwordInput.setError("Enter a password")
            return
        }

        // if email and pass is good, fire it up to firebase's ass
        loginWithFirebase(email, password)
    }

    fun loginWithFirebase(email : String, password : String) {
        setInProgress(true)
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email, password
        ).addOnSuccessListener {
            UiUtil.showToast(this, "Login successfull")
            setInProgress(false)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }.addOnFailureListener {
            binding.passwordInput.setError("Wrong credentials")
            setInProgress(false)
        }

    }
}