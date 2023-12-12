package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleWelcomeBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.firestore.firestore

class CircleWelcomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityCircleWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCircleWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = FirebaseAuth.getInstance().currentUser!!.uid;
        Firebase.firestore.collection("user")
            .document(userId)
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    val firstName = it.data?.get("firstName")?.toString()
                    binding.name.text = "Hi! " + firstName + " Now you can join or create your own Circle"
                }
            }
            .addOnFailureListener {
                UiUtil.showToast(this, "Failed to get name")
                binding.name.text = "default_name"
            }

        binding.welcomeContinueButton.setOnClickListener {
            startActivity(Intent(this, CircleClassActivity::class.java))
        }
    }
}