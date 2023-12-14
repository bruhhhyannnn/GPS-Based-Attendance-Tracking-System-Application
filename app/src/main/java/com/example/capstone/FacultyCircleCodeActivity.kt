package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.capstone.databinding.ActivityFacultyCircleCodeBinding
import com.example.capstone.model.CircleModel
import com.example.capstone.model.UserModel
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class FacultyCircleCodeActivity : AppCompatActivity() {

    lateinit var binding : ActivityFacultyCircleCodeBinding
    private val char = "123456789"
    private val generatedCodes = mutableSetOf<String>()
    private var circleCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyCircleCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        circleCode = generateCode()
        binding.codeInput.text = circleCode

        binding.facultyCircleShareCodeButton.setOnClickListener {

        //            TODO INPUT FOR COPYING THE CODE IN IMPLICIT INTENT


        }
        binding.goToLocationPermission.setOnClickListener {
            addCircleWithFirebase()
        }
    }

    fun setInProgress(inProgress : Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.goToLocationPermission.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.goToLocationPermission.visibility = View.VISIBLE
        }
    }

    fun generateCode(): String {
        val length = 7
        var code: String

        do {
            val sb = StringBuilder(length)

            for (x in 0 until length) {
                if (x == length / 2) { // Insert hyphen at the middle position
                    sb.append('-')
                } else {
                    val random = (char.indices).random()
                    sb.append(char[random])
                }
            }

            code = sb.toString()
        } while (generatedCodes.contains(code))

        generatedCodes.add(code)
        return code
    }

    fun addCircleWithFirebase() {
        val latitude = intent.extras?.getString("latitude").toString()
        val longitude = intent.extras?.getString("longitude").toString()
        val code = intent.extras?.getString("code").toString()
        val description = intent.extras?.getString("description").toString()
        val start_time = intent.extras?.getString("start_time").toString()
        val end_time = intent.extras?.getString("end_time").toString()

        setInProgress(true)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid;
        val circleID = Firebase.firestore.collection("circle").document()
        val circleModel = CircleModel(userId, circleCode, latitude, longitude, code, description, start_time, end_time)
        circleID.set(circleModel)
            .addOnSuccessListener {
                val newCircleId = circleID.id // Get the newly generated circle ID
                val userRef = Firebase.firestore.collection("user").document(userId)

                userRef.get()
                    .addOnSuccessListener { documentSnapshot ->
                        val userData = documentSnapshot.toObject(UserModel::class.java)
                        if (userData != null) {
                            val existingCircleIds = userData.circleID ?: mutableListOf()
                            existingCircleIds.add(newCircleId)

                            // Update the 'circleID' field in the 'user' collection with the updated list
                            userRef.update("circleID", existingCircleIds)
                                .addOnSuccessListener {
                                    UiUtil.showToast(this, "Created Circle and updated user data with circle ID!")
                                    setInProgress(false)
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                                .addOnFailureListener { userUpdateException ->
                                    UiUtil.showToast(this, "Failed to update user data: ${userUpdateException.message}")
                                    setInProgress(false)
                                }
                        } else {
                            UiUtil.showToast(this, "User data not found!")
                            setInProgress(false)
                        }
                    }
                    .addOnFailureListener { getUserDataException ->
                        UiUtil.showToast(this, "Failed to get user data: ${getUserDataException.message}")
                        setInProgress(false)
                    }
            }
            .addOnFailureListener { circleAddException ->
                UiUtil.showToast(this, "Failed to add circle data: ${circleAddException.message}")
                setInProgress(false)
            }
    }
}