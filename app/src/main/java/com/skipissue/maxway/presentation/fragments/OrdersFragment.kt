package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.HistoryFragmentBinding
import com.skipissue.maxway.presentation.adapter.ViewPagerAdapter

class OrdersFragment : Fragment(R.layout.history_fragment) {
    companion object {
        const val TAG = 2
    }
    private val binding: HistoryFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager2.adapter = ViewPagerAdapter(this)
    }
}