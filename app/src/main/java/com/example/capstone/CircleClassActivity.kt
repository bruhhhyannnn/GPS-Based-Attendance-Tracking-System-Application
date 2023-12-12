package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleClassBinding
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CircleClassActivity : AppCompatActivity() {

    lateinit var binding : ActivityCircleClassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCircleClassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
//            AUTH FOR VERIFICATION CODE
            validate()
        }

        binding.skip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.createCircleButton.setOnClickListener {
            startActivity(Intent(this, FacultyCircleDetailsActivity::class.java))
        }
    }

    fun validate() {
//        NOT VALIDATING
        var circle_code = binding.inputCode1.text.toString() + binding.inputCode2.text.toString() +
                            binding.inputCode3.text.toString() + binding.inputCode4.text.toString() +
                            binding.inputCode5.text.toString() + binding.inputCode6.text.toString()
        UiUtil.showToast(applicationContext, circle_code)
        Firebase.firestore.collection("circle")
            .whereEqualTo("circleCode", circle_code)
            .get()
            .addOnSuccessListener {
                startActivity(Intent(this, StudentCircleDetailsActivity::class.java))
                UiUtil.showToast(applicationContext, "Found Circle")

            }
            .addOnFailureListener {
                UiUtil.showToast(applicationContext, "Something went wrong")
            }
//        TODO VALIDATE INVITE CODE AND GENERATE A FUCKING INVITE CODE
//        val inviteCode = binding.inviteCodeInput.text.toString()
//
//        if (inviteCode.isEmpty()) {
//            binding.inviteCodeInput.setError("Invalid invite code")
//            return
//        }

//        AUTHENTICATE CODE HERE:


//            THEN IF YES
//        startActivity(Intent(this, StudentCircleDetailsActivity::class.java))
//            IF NO
//        binding.inviteCodeInput.setError("Invalid invite code")
    }
}