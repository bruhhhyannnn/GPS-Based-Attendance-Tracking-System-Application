package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleFacultyLocationBinding

class CircleFacultyLocationActivity : AppCompatActivity() {

    lateinit var binding : ActivityCircleFacultyLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCircleFacultyLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.circleFacultyLocationSaveButton.setOnClickListener {

//            VALIDATE THE PROPER LOCATION
//            THEN PROCEED TO OTHER CIRCLE INPUT FORM
        }
    }
}