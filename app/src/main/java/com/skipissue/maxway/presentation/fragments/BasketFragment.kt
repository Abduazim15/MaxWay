package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.BasketFragmentBinding
import com.skipissue.maxway.presentation.adapter.BasketAdapter
import com.skipissue.maxway.presentation.viewmodels.DataBaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.basket_fragment) {
    companion object {
        const val TAG = 1
    }
    private val binding: BasketFragmentBinding by viewBinding()
    private val adapter by lazy { BasketAdapter() }
    private val viewModel: DataBaseViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAll()
        binding.recycler.adapter = adapter
        viewModel.livedata.observe(viewLifecycleOwner){ data ->
            adapter.submitList(data)
            Toast.makeText(requireContext(), data.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

}
