package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.ChooseFragmentBinding
import com.skipissue.maxway.presentation.viewmodels.ProductsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChooseFragment : Fragment(R.layout.choose_fragment) {
    private val binding : ChooseFragmentBinding by viewBinding()
    private var counter = 1
    private var id :String? = null
    private val viewModel : ProductsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        id = arguments?.getString("id")
        viewModel.getProductWithDetails(id!!)
        lifecycleScope.launch {
            viewModel.stateSuccessD.collect{ data ->
                binding.apply {
                    description.setText(data.description.uz)
                    name.setText(data.title.uz)
                    title.setText(data.categories[0].title.uz)
                    Picasso.get().load("https://cdn.delever.uz/delever/"+data.image).into(binding.image)
                }
            }
        }
        binding.apply {
            dec.setOnClickListener {
                if (counter != 1) {
                    counter--
                    binding.amount.setText(counter.toString())
                }
            }
            inc.setOnClickListener {
                counter++
                binding.amount.setText(counter.toString())
            }
        }

    }
}
