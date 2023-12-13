package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.capstone.databinding.ActivityStudentCircleDetailsBinding
import com.example.capstone.model.StudentModel
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class StudentCircleDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityStudentCircleDetailsBinding
    lateinit var classID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentCircleDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        classID = intent.extras?.getString("documentId").toString()

        binding.circleStudentDetailsContinueButton.setOnClickListener {
            validate()
        }
    }

    fun setInProgress(inProgress : Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.circleStudentDetailsContinueButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.circleStudentDetailsContinueButton.visibility = View.VISIBLE
        }
    }

    fun validate() {
        val studentNum = binding.studentNumberInput.text.toString()
        val course = binding.courseInput.text.toString()
        val YRSection = binding.yearLevelNSectionInput.text.toString()

        if (studentNum.isEmpty()) {
            binding.studentNumberInput.setError("Enter a student number")
            return
        }
        if (course.isEmpty()) {
            binding.courseInput.setError("Enter a course")
            return
        }
        if (YRSection.isEmpty()) {
            binding.yearLevelNSectionInput.setError("Enter year level and section")
            return
        }

        addWithFirebase(studentNum, course, YRSection)
    }

    fun addWithFirebase(studentNum : String, course : String, YRSection : String) {
        setInProgress(true)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid;
        val studentModel = StudentModel(userId, studentNum, course, YRSection)
        Firebase.firestore.collection("student")
            .document()
            .set(studentModel)
            .addOnSuccessListener {
                Firebase.firestore.collection("user")
                    .document(userId)
                    .update("circleID", classID)
                    .addOnSuccessListener {
                        setInProgress(false)
                        startActivity(Intent(this, LocationPermissionActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        UiUtil.showToast(this, "Failed to add additional data")
                        setInProgress(false)
                    }
            }
            .addOnFailureListener {
                UiUtil.showToast(this, "Failed to add data")
                setInProgress(false)
            }
    }
}