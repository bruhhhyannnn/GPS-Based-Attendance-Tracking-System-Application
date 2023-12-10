package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleFacultyDetailsBinding
import com.example.capstone.databinding.ActivityCircleStudentDetailsBinding

class CircleFacultyDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityCircleFacultyDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCircleFacultyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.circleFacultyDetailsContinueButton.setOnClickListener {
            validate()
        }
    }
    fun validate() {
        val code = binding.codeInput.text.toString()
        val description = binding.descriptionInput.text.toString()

        if (code.isEmpty()) {
            binding.codeInput.setError("Enter a proper code")
            return
        }
        if (description.isEmpty()) {
            binding.descriptionInput.setError("Enter a proper description")
            return
        }

//          IF TEST CASES OKAY? GO TO ADD LOCATION FRAME
        startActivity(Intent(this, CircleFacultyLocationActivity::class.java))
    }
}