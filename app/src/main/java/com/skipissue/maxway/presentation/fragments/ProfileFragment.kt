package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.ProfileFragmentBinding

class ProfileFragment: Fragment(R.layout.profile_fragment) {
    private val binding : ProfileFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}