package com.skipissue.maxway.presentation.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.LocationServices
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.LocationFragmentBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer

class LocationFragment : Fragment(R.layout.location_fragment) {
    private val binding: LocationFragmentBinding by viewBinding()
    private lateinit var locationManager: LocationManager
    private lateinit var userLocationLayer: UserLocationLayer
    private lateinit var mapView: MapView
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart();
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }



    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requestLocationPermission()
        mapView = binding.mapView
        binding.current.setOnClickListener {
            var fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

            fusedLocationClient.lastLocation.addOnSuccessListener { location: android.location.Location? ->
                if (location != null) {
                    goToLocation(location.latitude, location.longitude)
                }
            }
        }
    }
    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            goToLocation(41.207608, 69.184516)


        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }
    private fun goToLocation(latitude: Double, longitude: Double) {
        val map = binding.mapView.map
        map.isRotateGesturesEnabled = false // Disable rotation of the map if desired
        val cameraPosition = CameraPosition(
            Point(latitude, longitude),
            15.0f, // Specify the desired zoom level here
            0.0f, // Specify the desired azimuth (map rotation) here if needed
            0.0f // Specify the desired tilt (camera pitch) here if needed
        )

        map.move(
            cameraPosition,
            Animation(Animation.Type.SMOOTH, 1.5f),
            null
        )
    }
}