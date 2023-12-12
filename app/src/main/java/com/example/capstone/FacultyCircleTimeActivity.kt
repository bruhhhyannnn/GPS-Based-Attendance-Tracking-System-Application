package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.capstone.databinding.ActivityFacultyCircleTimeBinding
import com.example.capstone.util.UiUtil

class FacultyCircleTimeActivity : AppCompatActivity() {

    lateinit var binding : ActivityFacultyCircleTimeBinding
    lateinit var start_time : String
    lateinit var end_time : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyCircleTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTime()

        binding.circleFacultyTimeContinueButton.setOnClickListener {
            validate()
        }
    }

    fun setInProgress(inProgress : Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.circleFacultyTimeContinueButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.circleFacultyTimeContinueButton.visibility = View.VISIBLE
        }
    }

    fun validate() {
        val date = binding.dateInput.text.toString()

        if (date.isEmpty()) {
            binding.dateInput.setError("Enter a proper date")
            return
        }


//        PUT TO DATABASE
        circleWithFirebase()
    }

    fun circleWithFirebase() {
        setInProgress(true)
        setInProgress(false)

//        ADD TO DATABASE THEN:
        startActivity(Intent(this, FacultyCircleCodeActivity::class.java))
        finish()
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
        var start_hour = ""
        var start_minute = ""
        var start_period = ""
        var end_hour = ""
        var end_minute = ""
        var end_period = ""

        binding.startHour.minValue = 1
        binding.startHour.maxValue = 12
        binding.startHour.setOnValueChangedListener { _, _, newVal ->
//            start_hour = newVal.toString()
            start_time = newVal.toString() + ":$start_minute $start_period"
        }

        binding.startMinute.minValue = 0
        binding.startMinute.maxValue = 59
        binding.startMinute.displayedValues = minutes
        binding.startMinute.setOnValueChangedListener { _, _, newVal ->
//            start_minute = minutes[newVal]
            start_time = "$start_hour:" + minutes[newVal] + " $start_period"
        }

        binding.startPeriod.minValue = 0
        binding.startPeriod.maxValue = 1
        binding.startPeriod.displayedValues = periods
        binding.startPeriod.setOnValueChangedListener { _, _, newVal ->
//            start_period = periods[newVal]
            start_time = "$start_hour:$start_minute" + periods[newVal]
        }


        binding.endHour.minValue = 1
        binding.endHour.maxValue = 12
        binding.endHour.setOnValueChangedListener { _, _, newVal ->
//            end_hour = newVal.toString()
            end_time = newVal.toString() + "$:$end_minute $end_period"
        }

        binding.endMinute.minValue = 0
        binding.endMinute.maxValue = 59
        binding.endMinute.displayedValues = minutes
        binding.endMinute.setOnValueChangedListener { _, _, newVal ->
//            end_minute =
            end_time = "$end_hour:" + minutes[newVal] + " $end_period"
        }

        binding.endPeriod.minValue = 0
        binding.endPeriod.maxValue = 1
        binding.endPeriod.displayedValues = periods
        binding.endPeriod.setOnValueChangedListener { _, _, newVal ->
//            end_period = periods[newVal]
            end_time = "$end_hour:$end_minute" + periods[newVal]
        }
    }

    fun updateStartTime(start_hour : String, start_minute : String ,start_period : String) {
        start_time = "$start_hour:$start_minute $start_period"
    }
    fun updateEndTime(end_hour : String, end_minute : String ,end_period : String) {
        end_time = "$end_hour:$end_minute $end_period"
    }

}