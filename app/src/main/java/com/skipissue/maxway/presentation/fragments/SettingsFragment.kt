package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.SettingsFragmentBinding
import com.skipissue.maxway.presentation.BottomSheet

class SettingsFragment: Fragment(R.layout.settings_fragment){
    private val binding: SettingsFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bottomsheet = BottomSheet()
        binding.language.setOnClickListener {
            bottomsheet.show(parentFragmentManager, "language")
        }
    }

    override fun onDestroy() {
        (requireActivity() as MainActivity).hideOrShow(false)
        super.onDestroy()
    }
}