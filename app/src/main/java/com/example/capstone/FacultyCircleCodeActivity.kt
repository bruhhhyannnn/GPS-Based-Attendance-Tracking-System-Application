package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityFacultyCircleCodeBinding

class FacultyCircleCodeActivity : AppCompatActivity() {

    lateinit var binding : ActivityFacultyCircleCodeBinding
    private val char = "123456789"
    private val generatedCodes = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyCircleCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.codeInput.text = generateCode()

        binding.facultyCircleShareCodeButton.setOnClickListener {

        //            INPUT FOR COPYING THE CODE IN IMPLICIT INTENT


        }

        binding.goToLocationPermission.setOnClickListener {
//            GO NOW TO MAIN ACTIVITY
            startActivity(Intent(this, LocationPermissionActivity::class.java))
        }
    }

    fun generateCode(): String {
        val length = 7
        var code: String

        do {
            val sb = StringBuilder(length)

            for (x in 0 until length) {
                if (x == length / 2) { // Insert hyphen at the middle position
                    sb.append('-')
                } else {
                    val random = (char.indices).random()
                    sb.append(char[random])
                }
            }

            code = sb.toString()
        } while (generatedCodes.contains(code))

        generatedCodes.add(code)
        return code
    }
}