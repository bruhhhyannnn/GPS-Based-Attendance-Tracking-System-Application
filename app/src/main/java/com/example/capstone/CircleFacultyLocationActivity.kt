package com.example.capstone

import android.content.Intent
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
        startActivity(Intent(this, CircleFacultyTimeActivity::class.java))
    }
}