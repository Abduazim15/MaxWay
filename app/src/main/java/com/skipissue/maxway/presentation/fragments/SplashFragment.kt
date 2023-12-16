package com.skipissue.maxway.presentation.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.skipissue.maxway.R
import com.skipissue.maxway.data.settings.Settings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment :
    Fragment(R.layout.splash_fragment) {
    @Inject
    lateinit var settings: Settings

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = resources.getColor(R.color.primary, null)
        object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                if (settings.language == 0)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, SelectLanguageFragment()).commit()
                else
                    parentFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
                        .commit()
                requireActivity().window.statusBarColor = Color.WHITE
            }
        }.start()
    }
}