package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityHomeBinding
import com.example.capstone.databinding.ActivityLoginPasswordBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        FOR CHECKING IF USER ALREADY LOGGED IN
//        FirebaseAuth.getInstance().currentUser?.let {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }

        binding.getStartedButton.setOnClickListener {
            startActivity(Intent(this, SignupPersonalActivity::class.java))
        }

        binding.goToLogin.setOnClickListener {
            startActivity(Intent(this, LoginEmailActivity::class.java))
        }
    }
}