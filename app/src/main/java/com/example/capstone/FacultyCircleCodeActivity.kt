package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityCircleFacultyTimeBinding
import com.example.capstone.databinding.ActivityFacultyCircleCodeBinding

class FacultyCircleCodeActivity : AppCompatActivity() {

    lateinit var binding : ActivityFacultyCircleCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyCircleCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.facultyCircleShareCodeButton.setOnClickListener {

        //            INPUT FOR COPYING THE CODE IN IMPLICIT INTENT


        }

        binding.goToDashboard.setOnClickListener {
//            GO NOW TO MAIN ACTIVITY
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}