package com.skipissue.maxway.presentation.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.skipissue.maxway.R

class SplashFragment : Fragment(R.layout.splash_fragment) {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = resources.getColor(R.color.primary, null)
        object : CountDownTimer(1000, 2000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                parentFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
            }
        }.start()
    }
}