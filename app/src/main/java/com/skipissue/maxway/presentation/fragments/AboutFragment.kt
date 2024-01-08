package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.AboutFragmentBinding

class AboutFragment : Fragment(R.layout.about_fragment) {
    private val binding: AboutFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.about.setOnClickListener {
            parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                .addToBackStack("AboutFragment").replace(R.id.container, AboutUsFragment()).commit()
        }
        binding.publicAfford.setOnClickListener {
            parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                .addToBackStack("AboutFragment").replace(R.id.container, PublicAffordFragment()).commit()
        }
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).hideOrShow(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as MainActivity).hideOrShow(false)

    }
}