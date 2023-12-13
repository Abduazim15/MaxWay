package com.skipissue.maxway.presentation.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
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
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
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
        mapView.map.addCameraListener(object : CameraListener {
            override fun onCameraPositionChanged(
                p0: Map,
                p1: CameraPosition,
                p2: CameraUpdateReason,
                p3: Boolean
            ) {
                if (p3) {
                    val target = p1.target
                    val point = Point(target.latitude, target.longitude)
                }
            }


        })
        binding.current.setOnClickListener {
            var fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(requireActivity())

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