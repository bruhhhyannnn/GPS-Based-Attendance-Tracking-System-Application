package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsAccountBinding
import com.example.capstone.databinding.ActivitySettingsAppPermissionBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class SettingsAccountActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingsAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        Firebase.firestore.collection("user")
            .document(userId)
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    val firstName = it.data?.get("firstName")?.toString()
                    val lastName = it.data?.get("lastName")?.toString()
                    binding.name.text = firstName + " " + lastName
                }
            }
            .addOnFailureListener {
                UiUtil.showToast(this, "Failed to get name")
            }

        binding.changePassword.setOnClickListener {

        }
        binding.deleteAccount.setOnClickListener {

        }
    }
}