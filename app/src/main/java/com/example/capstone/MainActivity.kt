package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.util.UiUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(18.059528, 120.544957)
        val options : MarkerOptions = MarkerOptions().position(location).title("Your Circle")
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        googleMap.addMarker(options)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}