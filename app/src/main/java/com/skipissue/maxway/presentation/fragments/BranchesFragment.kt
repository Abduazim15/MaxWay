package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.BranchesFragmentBinding
import com.skipissue.maxway.presentation.adapter.BranchAdapter
import com.skipissue.maxway.presentation.viewmodels.BranchesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BranchesFragment : Fragment(R.layout.branches_fragment) {
    private val binding: BranchesFragmentBinding by viewBinding()
    private val viewModel: BranchesViewModel by viewModels()
    private val adapter by lazy { BranchAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.stateSuccess.collect { data ->
                adapter.submitList(data.branches)
            }
        }
        adapter.setOnClickClickListener { index ->
            parentFragmentManager.beginTransaction().addToBackStack("BranchesFragment")
                .replace(R.id.container, BranchFragment::class.java, bundleOf(
                    "name" to adapter.currentList[index].address,
                    "orient" to adapter.currentList[index].destination,
                    "timeStart" to adapter.currentList[index].work_hour_start,
                    "timeEnd" to adapter.currentList[index].work_hour_end,
                    "phone" to adapter.currentList[index].phone,
                )).commit()
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