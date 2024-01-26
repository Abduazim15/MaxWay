package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.BasketFragmentBinding
import com.skipissue.maxway.domain.entity.FoodHistoryEntity
import com.skipissue.maxway.presentation.adapter.BasketAdapter
import com.skipissue.maxway.presentation.dialogs.DeleteDialog
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
        viewModel.livedata.observe(viewLifecycleOwner) { data ->
            adapter.submitList(data)
        }
        adapter.setOnDecClickClickListener { index, quantity ->
            if (quantity > 0)
                viewModel.increaseAmount(adapter.currentList[index].id.toInt(), -1)
            else if (quantity == 0)
                DeleteDialog(requireContext()) { isOk ->
                    if (isOk) {
                        viewModel.deleteById(adapter.currentList[index].id.toInt())
                        val list = adapter.currentList.toMutableList()
                        list.removeAt(index)
                        adapter.submitList(list)
                    }
                }.show()
        }
        adapter.setOnIncClickClickListener { index, quantity ->
            if (quantity < 100)
                viewModel.increaseAmount(adapter.currentList[index].id.toInt(), 1)
        }
        adapter.setOnClickClickListener { index ->
            DeleteDialog(requireContext()){clicked ->
                if (clicked){
                    viewModel.deleteById(adapter.currentList[index].id.toInt())
                    val list = adapter.currentList.toMutableList()
                    list.removeAt(index)
                    adapter.submitList(list)
                }
            }.show()
        }
        binding.delete.setOnClickListener {
            DeleteDialog(requireContext()){clicked ->
                if (clicked){
                    viewModel.delete()
                    adapter.submitList(emptyList())
                }
            }.show()

        }
        adapter.setOnListChangedListener {
            if (adapter.currentList.isNullOrEmpty()){

            } else {

            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

}
