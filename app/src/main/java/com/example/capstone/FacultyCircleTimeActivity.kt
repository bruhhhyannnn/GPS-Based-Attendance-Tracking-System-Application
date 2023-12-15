package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.capstone.databinding.ActivityFacultyCircleTimeBinding
import com.example.capstone.model.StudentModel
import com.example.capstone.util.UiUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class FacultyCircleTimeActivity : AppCompatActivity() {

    lateinit var binding : ActivityFacultyCircleTimeBinding
    var start_hour = "1"
    var start_minute = "00"
    var start_period = "AM"
    var end_hour = "1"
    var end_minute = "00"
    var end_period = "AM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyCircleTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTime()

        binding.circleFacultyTimeContinueButton.setOnClickListener {
            val start_time = "$start_hour:$start_minute $start_period"
            val end_time = "$end_hour:$end_minute $end_period"
            val latitude = intent.extras?.getString("latitude").toString()
            val longitude = intent.extras?.getString("longitude").toString()
            val code = intent.extras?.getString("code").toString()
            val description = intent.extras?.getString("description").toString()

            val intent = Intent(this, FacultyCircleCodeActivity::class.java)
            intent.putExtra("latitude", latitude)
            intent.putExtra("longitude", longitude)
            intent.putExtra("code", code)
            intent.putExtra("description", description)
            intent.putExtra("start_time", start_time)
            intent.putExtra("end_time", end_time)
            startActivity(intent)
            finish()
        }
    }

    fun setTime() {
        val minutes: Array<String> = arrayOf(
            "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59"
        )
        val periods: Array<String> = arrayOf(
            "AM",
            "PM"
        )

        binding.startHour.minValue = 1
        binding.startHour.maxValue = 12
        binding.startHour.setOnValueChangedListener { _, _, newVal ->
            start_hour = newVal.toString()
        }

        binding.startMinute.minValue = 0
        binding.startMinute.maxValue = 59
        binding.startMinute.displayedValues = minutes
        binding.startMinute.setOnValueChangedListener { _, _, newVal ->
            start_minute = minutes[newVal]
        }

        binding.startPeriod.minValue = 0
        binding.startPeriod.maxValue = 1
        binding.startPeriod.displayedValues = periods
        binding.startPeriod.setOnValueChangedListener { _, _, newVal ->
            start_period = periods[newVal]
        }

        binding.endHour.minValue = 1
        binding.endHour.maxValue = 12
        binding.endHour.setOnValueChangedListener { _, _, newVal ->
            end_hour = newVal.toString()
        }

        binding.endMinute.minValue = 0
        binding.endMinute.maxValue = 59
        binding.endMinute.displayedValues = minutes
        binding.endMinute.setOnValueChangedListener { _, _, newVal ->
            end_minute = minutes[newVal]
        }

        binding.endPeriod.minValue = 0
        binding.endPeriod.maxValue = 1
        binding.endPeriod.displayedValues = periods
        binding.endPeriod.setOnValueChangedListener { _, _, newVal ->
            end_period = periods[newVal]
        }
    }
}