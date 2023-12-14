package com.example.capstone

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.util.UiUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    lateinit var binding : ActivityMainBinding
    lateinit var mMap: GoogleMap
    lateinit var lastLocation : Location
    lateinit var fusedLocation : FusedLocationProviderClient
    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        fusedLocation = LocationServices.getFusedLocationProviderClient(this)

        validate()

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, MainSettingsActivity::class.java))
        }

        binding.notificationsButton.setOnClickListener {
            startActivity(Intent(this, MainNotificationActivity::class.java))
        }

        binding.circleButton.setOnClickListener {
            startActivity(Intent(this, MainCircleActivity::class.java))
        }

        binding.bottomNavBar.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_menu_home -> {
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

    fun validate() {
//        println(DateFormat.getDateInstance(DateFormat.DATE_FIELD))
//        println(DateFormat.getDateInstance(DateFormat.FULL))
        println(DateFormat.getTimeInstance(DateFormat.SHORT))

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        Firebase.firestore.collection("circle")
            .whereEqualTo("userId", userId) // Assuming userId field in documents is named "userId"
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val startTime = document.get("startTime").toString() // Replace "startTime" with your actual field name
                    val endTime = document.get("endTime").toString() // Replace "endTime" with your actual field name
                    val latitude = document.get("latitude").toString() // Replace "endTime" with your actual field name
                    val longitude = document.get("longitude").toString() // Replace "endTime" with your actual field name


                }
            }
            .addOnFailureListener { exception ->
                UiUtil.showToast(this, "Failed to get user: ${exception.message}")
            }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setupMap()

        val location = LatLng(18.059754, 120.544870)
        val options = MarkerOptions().position(location).title("circle_name")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))

        googleMap.addMarker(options)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 20.0f))
        googleMap.addCircle(
            CircleOptions()
                .center(location)
                .radius(7.0)
                .fillColor(Color.argb(40, 0, 122, 255))
                .strokeColor(Color.rgb(0, 122, 255))
                .strokeWidth(5f)
        )
    }

    fun validateIfInsideCircle(latitude : Double, longitude : Double, circleCenterLatitude : Double, circleCenterLongitude : Double) {
        // Assuming you have the user's current location as a LatLng object named currentUserLocation
// Assuming you have the circle's center and radius as LatLng and radius variables respectively

        val distance = FloatArray(1)
        Location.distanceBetween(
            latitude, longitude,
            circleCenterLatitude, circleCenterLongitude,
            distance
        )

// Check if the distance is less than the circle's radius
        val radius = 7.0 // Assuming circle radius is 7.0 (in meters)
        if (distance[0] < radius) {
            // User is inside the circle
            // Perform actions or show a message indicating the user is inside the circle
            UiUtil.showToast(applicationContext, "Inside Circle!")
        } else {
            // User is outside the circle
            // Perform actions or show a message indicating the user is outside the circle
            UiUtil.showToast(applicationContext, "Not Inside Circle!")
        }

    }

    fun setupMap() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocation.lastLocation.addOnSuccessListener(this) {location ->
            if(location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLong)
                validateIfInsideCircle(location.latitude, location.longitude, 37.421987, -122.083943)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 20.0f))
            }
        }
    }

    private fun placeMarkerOnMap(currentLatLong : LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("bryan mangapit")
        mMap.addMarker(markerOptions)
    }

    override fun onMarkerClick(p0: Marker)= false
}