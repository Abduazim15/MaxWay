package com.skipissue.maxway.presentation.fragments

import androidx.fragment.app.Fragment
import com.skipissue.maxway.R
import com.yandex.mapkit.MapKitFactory

class LocationFragment : Fragment(R.layout.location_fragment) {
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }
    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}