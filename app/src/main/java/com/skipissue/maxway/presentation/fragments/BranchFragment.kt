package com.skipissue.maxway.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.BranchFragmentBinding

class BranchFragment : Fragment(R.layout.branch_fragment) {

    private val binding: BranchFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            locationText.setText(arguments?.getString("name"))
            orientorText.setText(arguments?.getString("orient"))
            timeText.setText("${arguments?.getString("timeStart")} - ${arguments?.getString("timeEnd")}")
            phoneText.setText(ProfileFragment().formatPhoneNumber(arguments?.getString("phone")!!))
            phone.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${phoneText.text}")
                startActivity(intent)
            }
            back.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        }

    }

}
