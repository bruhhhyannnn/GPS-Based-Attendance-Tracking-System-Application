package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityFacultyCircleLocationBinding

class FacultyCircleLocationActivity : AppCompatActivity() {

    lateinit var binding : ActivityFacultyCircleLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyCircleLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.circleFacultyLocationSaveButton.setOnClickListener {
            validate()
        }
    }
    fun validate() {
        val address = binding.addressInput.text.toString()

        if (address.isEmpty()) {
            binding.addressInput.setError("Enter a proper address")
            return
        }

//          IF TEST CASES OKAY? GO TO ADD TIME FRAME
        startActivity(Intent(this, FacultyCircleTimeActivity::class.java))
        finish()
    }
}