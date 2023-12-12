package com.skipissue.maxway.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.LocationFragmentBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class LocationFragment : Fragment(R.layout.location_fragment) {
    private val binding: LocationFragmentBinding by viewBinding()
    private lateinit var locationManager: LocationManager
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MapKitFactory.setApiKey("58099092-9143-4132-a9e9-c68efd2ddfbd")
        MapKitFactory.initialize(requireContext())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requestLocationPermission()
        locationManager = MapKitFactory.getInstance().createLocationManager()
        mapView = binding.mapView
    }
    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            goToCurrentLocation()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }
    private fun goToCurrentLocation() {
        // Request the user's current location
        locationManager.requestSingleUpdate(object : LocationListener {
            override fun onLocationUpdated(location: Location) {
                // Location update received
                val latitude = location.position.latitude
                val longitude = location.position.longitude

                // Go to the current location on the map
                goToLocation(latitude, longitude)
            }

            override fun onLocationStatusUpdated(locationStatus: LocationStatus) {
                // Location status updated
                // Handle location status changes if needed
            }
        })
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

        // Move the camera to the specified position
        map.move(
            cameraPosition
        )
    }
}