package com.example.capstone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.util.UiUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        addCircle()

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, MainSettingsActivity::class.java))
        }

        binding.notificationsButton.setOnClickListener {
            startActivity(Intent(this, MainNotificationActivity::class.java))
        }

//        TODO
//        binding.settingsButton.setOnClickListener {
//            startActivity(Intent(this, MainClassActivity::class.java))
//        }

        binding.bottomNavBar.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_menu_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                R.id.bottom_menu_circle -> {
                    UiUtil.showToast(this, "nakulangan ng oras para gawin to xd, no, actually if nasapsapa na istart tuy code naubra kuma etuy")
                    startActivity(Intent(this, MainActivity::class.java))

                }
                R.id.bottom_menu_idk -> {
                    UiUtil.showToast(this, "nakulangan ng oras para gawin to xd, no, actually if nasapsapa na istart tuy code naubra kuma etuy")
                }
                R.id.bottom_menu_profile -> {
                    startActivity(Intent(this, MainProfileActivity::class.java))
                }
            }
            false

        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(18.059754, 120.544870)
        val options : MarkerOptions = MarkerOptions().position(location).title("Your Circle")

        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        googleMap.addMarker(options)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 20.0f))

        googleMap.addCircle(CircleOptions()
            .center(location)
            .radius(7.0)
            .fillColor(Color.argb(40, 0, 122, 255))
            .strokeColor(Color.rgb(0, 122, 255))
            .strokeWidth(5f)
        )
        googleMap.uiSettings.isMyLocationButtonEnabled = true
    }

    fun addCircle() {

    }
}