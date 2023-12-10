package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.util.UiUtil

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavBar.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_menu_home -> {
                    UiUtil.showToast(this, "Home")
                }
                R.id.bottom_menu_idk -> {
                    UiUtil.showToast(this, "ugh dpa kasi tinapos")
                }
                R.id.bottom_menu_profile -> {
                    UiUtil.showToast(this, "Profile")
                }
            }
            false

        }
    }
}