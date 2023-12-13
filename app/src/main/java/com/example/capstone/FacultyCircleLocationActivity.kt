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
        val latitude = binding.latitudeInput.text.toString()
        val longitude = binding.longitudeInput.text.toString()
        val code = intent.extras?.getString("code").toString()
        val description = intent.extras?.getString("description").toString()

//        val address = binding.addressInput.text.toString()
//
//        if (address.isEmpty()) {
//            binding.addressInput.setError("Enter a proper address")
//            return
//        }

        val intent = Intent(this, FacultyCircleTimeActivity::class.java)
        intent.putExtra("latitude", latitude)
        intent.putExtra("longitude", longitude)
        intent.putExtra("code", code)
        intent.putExtra("description", description)
        startActivity(intent)
    }
}