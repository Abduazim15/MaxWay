package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.ChooseFragmentBinding

class ChooseFragment : Fragment(R.layout.choose_fragment) {
    private val binding : ChooseFragmentBinding by viewBinding()
    private var counter = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dec.setOnClickListener {
            if (counter != 1){
                counter--
                binding.amount.setText(counter.toString())
            }
        }
        binding.inc.setOnClickListener {
            counter++
            binding.amount.setText(counter.toString())
        }
    }
}
