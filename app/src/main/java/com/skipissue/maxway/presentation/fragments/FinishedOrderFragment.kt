package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.ActiveFragmentBinding
import com.skipissue.maxway.presentation.adapter.HistoryAdapter
import com.skipissue.maxway.presentation.viewmodels.HistoryViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FinishedOrderFragment : Fragment(R.layout.active_fragment) {
    private val binding: ActiveFragmentBinding by viewBinding()
    private val adapter by lazy { HistoryAdapter() }
    private val viewModel: HistoryViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recycler.adapter = adapter
        binding.get.setOnClickListener {
            viewModel.getOrders()
        }
        lifecycleScope.launch {
            viewModel.stateSuccess.collect{ data ->
                adapter.submitList(data.orders)
            }
        }
    }

}
