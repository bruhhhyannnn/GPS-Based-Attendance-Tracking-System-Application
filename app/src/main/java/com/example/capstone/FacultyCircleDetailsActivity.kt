package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityFacultyCircleDetailsBinding

class FacultyCircleDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityFacultyCircleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyCircleDetailsBinding.inflate(layoutInflater)
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
        startActivity(Intent(this, FacultyCircleLocationActivity::class.java))
        finish()
    }
}