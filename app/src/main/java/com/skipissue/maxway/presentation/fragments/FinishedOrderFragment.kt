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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FinishedOrderFragment : Fragment(R.layout.active_fragment) {
    private val binding: ActiveFragmentBinding by viewBinding()
    private val adapter by lazy { HistoryAdapter() }
    private val viewModel: HistoryViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.stateSuccess.collect{ data ->
                adapter.submitList(data.orders)
            }
        }
        adapter.setOnClickClickListener { index ->

        }
    }

}
