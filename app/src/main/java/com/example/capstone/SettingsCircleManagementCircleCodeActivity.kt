package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsCircleManagementCircleCodeBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class SettingsCircleManagementCircleCodeActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsCircleManagementCircleCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsCircleManagementCircleCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leftArrowButton.setOnClickListener {
            startActivity(Intent(this, SettingsCircleManagementActivity::class.java))
            finish()
        }

        showCircleCode()

        binding.shareCodeButton.setOnClickListener {
//            TODO CODE FOR SHARING IT TO IMPLICT INTENT
        }
    }

    fun showCircleCode() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        Firebase.firestore.collection("circle")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    for (document in documents) {
                        val circleCode = document.getString("circleCode")
                        if (circleCode != null) {
                            binding.codeInput.text = circleCode
                        } else {
                            UiUtil.showToast(this, "Circle code not found")
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                UiUtil.showToast(this, "Failed to get circle code: ${e.message}")
            }
    }
}