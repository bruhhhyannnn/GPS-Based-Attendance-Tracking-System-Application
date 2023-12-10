package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleFacultyTimeBinding

class CircleFacultyTimeActivity : AppCompatActivity() {

    lateinit var binding : ActivityCircleFacultyTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCircleFacultyTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.circleFacultyTimeContinueButton.setOnClickListener {
            validate()
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