package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.data.constants.Languages
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.databinding.SelectLanguageFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectLanguageFragment () :
    Fragment(R.layout.select_language_fragment) {
    private val binding: SelectLanguageFragmentBinding by viewBinding()
    @Inject
    lateinit var settings: Settings
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            eng.setOnClickListener {
                settings.language = Languages.ENG
                replace()
            }
            uzb.setOnClickListener {
                settings.language = Languages.UZB
                replace()
            }
            rus.setOnClickListener {
                settings.language = Languages.RUS
                replace()












            }

        }
    }
    private fun replace(){
        parentFragmentManager.beginTransaction().setReorderingAllowed(true)
            .replace(R.id.container, MainScreensFragment()).commit()
    }


}
