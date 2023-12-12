package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.capstone.databinding.ActivityFacultyCircleTimeBinding

class FacultyCircleTimeActivity : AppCompatActivity() {

    lateinit var binding : ActivityFacultyCircleTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyCircleTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.circleFacultyTimeContinueButton.setOnClickListener {
            validate()
        }
    }

    fun setInProgress(inProgress : Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.circleFacultyTimeContinueButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.circleFacultyTimeContinueButton.visibility = View.VISIBLE
        }
    }

    fun validate() {
        val date = binding.dateInput.text.toString()
        val time = binding.timeInput.text.toString()

        if (date.isEmpty()) {
            binding.dateInput.setError("Enter a proper date")
            return
        }
        if (time.isEmpty()) {
            binding.timeInput.setError("Enter a proper time")
            return
        }

//        PUT TO DATABASE
        circleWithFirebase()
    }

    fun circleWithFirebase() {

//        ADD TO DATABASE THEN:
        startActivity(Intent(this, FacultyCircleCodeActivity::class.java))
        finish()
    }
}