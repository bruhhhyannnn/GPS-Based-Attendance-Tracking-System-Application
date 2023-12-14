package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySettingsAccountBinding
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

        binding.changePassword.setOnClickListener {
            startActivity(Intent(this, SettingsAccountChangePasswordActivity::class.java))
        }
        binding.deleteAccount.setOnClickListener {
            startActivity(Intent(this, SettingsAccountDeleteAccountActivity::class.java))
        }
    }

    fun addProfile() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid;
        Firebase.firestore.collection("user")
            .document(userId)
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    val title = it.data?.get("title")?.toString()
                    val firstName = it.data?.get("firstName")?.toString()
                    val lastName = it.data?.get("lastName")?.toString()
                    binding.name.text = title + " " +  firstName + " " + lastName
                }
            }
            .addOnFailureListener {
                UiUtil.showToast(this, "Failed to get name")
                binding.name.text = "default_name"
            }
    }
}