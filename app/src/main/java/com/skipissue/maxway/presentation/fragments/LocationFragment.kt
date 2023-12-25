package com.skipissue.maxway.presentation.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.LocationServices
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.databinding.LocationFragmentBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LocationFragment : Fragment(R.layout.location_fragment) {
    private val binding: LocationFragmentBinding by viewBinding()
    private lateinit var mapView: MapView
    private val _liveData: MutableLiveData<String?> = MutableLiveData()
    private val liveData: LiveData<String?> = _liveData
    var lat = 0.0
    var lon = 0.0

    @Inject
    lateinit var settings: Settings
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart();
        (requireActivity() as MainActivity).hideOrShow(true)
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
                    val latitude = p1.target.latitude
                    val longitude = p1.target.longitude
                    lat = latitude
                    lon = longitude
                    searchForAddress(latitude, longitude)
                }

            }

        })
        binding.apply {
            submit.setOnClickListener {
                settings.location = name.text.toString()
                settings.lat = lat.toFloat()
                settings.lon = lon.toFloat()
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            name.setText(settings.location)
            current.setOnClickListener {
                var fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(requireActivity())

                fusedLocationClient.lastLocation.addOnSuccessListener { location: android.location.Location? ->
                    if (location != null) {
                        goToLocation(location.latitude, location.longitude)
                    }
                }
            }
        }
        liveData.observe(viewLifecycleOwner) { data ->
            binding.name.setText(data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as MainActivity).hideOrShow(false)

    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (settings.lat != null && settings.lon != null)
                goToLocation(settings.lat!!.toDouble(), settings.lon!!.toDouble())
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

    private fun searchForAddress(latitude: Double, longitude: Double) {
        val searchManager =
            SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)
        val options = SearchOptions()

        val point = Point(latitude, longitude)
        searchManager.submit(
            point,
            null,
            options,
            object : Session.SearchListener {
                override fun onSearchResponse(response: Response) {
                    val resultList = response.collection.children.mapNotNull { it.obj }
                    if (resultList.isNotEmpty()) {
                        val topResult = resultList[0]
                        val addressName = topResult.name
                        _liveData.postValue(addressName)

                    } else {

                    }
                }

                override fun onSearchError(p0: com.yandex.runtime.Error) {
                }


            }
        )
    }
}