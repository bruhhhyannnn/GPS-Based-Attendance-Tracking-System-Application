package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleStudentDetailsBinding

class CircleStudentDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityCircleStudentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCircleStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.circleStudentDetailsContinueButton.setOnClickListener {
            validate()
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
//          IF ALL TEST CASES SUCCEEDED GO TO:
        addWithFirebase(studentNum, course, YRSection)
    }

    fun addWithFirebase(studentNum : String, course : String, YRSection : String) {

//        UPDATE OPERATION

//          IF ADDED GO TO LOCATION PERMISSION FRAME
//        startActivity(Intent(this, ::class.java))

    }
}