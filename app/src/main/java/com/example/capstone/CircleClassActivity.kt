package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            addAdditionalsWithFirestore()
            finish()
        }

        binding.skip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.createCircleButton.setOnClickListener {
            startActivity(Intent(this, FacultyCircleDetailsActivity::class.java))
            finish()
        }
    }

    fun setInProgress(inProgress : Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.submitButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.submitButton.visibility = View.VISIBLE
        }
    }

    fun addAdditionalsWithFirestore() {
        setInProgress(true)
        val circle_code =
                    binding.inputCode1.text.toString() + binding.inputCode2.text.toString() +
                    binding.inputCode3.text.toString() + "-" + binding.inputCode4.text.toString() +
                    binding.inputCode5.text.toString() + binding.inputCode6.text.toString()

        Firebase.firestore.collection("circle")
            .whereEqualTo("circleCode", circle_code)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val document = querySnapshot.documents[0]
                    val circleCode = document.getString("circleCode")

                    if (!circleCode.isNullOrEmpty()) {
                        UiUtil.showToast(applicationContext, "Found circle!")
                        setInProgress(false)
                        startActivity(Intent(this, StudentCircleDetailsActivity::class.java)
                            .putExtra("documentId", document.id))
                    } else {
                        UiUtil.showToast(applicationContext, "Circle code not found")
                        setInProgress(false)
                    }
                } else {
                    UiUtil.showToast(applicationContext, "Circle not found")
                    setInProgress(false)
                }
            }
            .addOnFailureListener {
                UiUtil.showToast(applicationContext, "Something went wrong")
                setInProgress(false)
            }
    }
}