package com.skipissue.maxway.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.ChooseFragmentBinding
import com.skipissue.maxway.presentation.viewmodels.ProductsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class ChooseFragment : Fragment(R.layout.choose_fragment) {
    private val binding: ChooseFragmentBinding by viewBinding()
    private var counter = 1
    private var id: String? = null
    private val viewModel: ProductsViewModel by viewModels()
    private var price = 0
    private val numberFormat = NumberFormat.getInstance(Locale.getDefault())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        id = arguments?.getString("id")
        viewModel.getProductWithDetails(id!!)
        lifecycleScope.launch {
            viewModel.stateSuccessD.collect { data ->
                binding.apply {
                    description.setText(data.description.uz)
                    name.setText(data.title.uz)
                    title.setText(data.categories[0].title.uz)
                    Picasso.get().load("https://cdn.delever.uz/delever/" + data.image)
                        .into(binding.image)
                    sum.setText("${numberFormat.format(data.out_price)} UZS")
                    price = data.out_price
                    back.setOnClickListener {
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                    add.setOnClickListener {
                        showSnackbar(view as ViewGroup, "Savatga qo'shildi", resources.getColor(R.color.primary, null))
                    }
                }
            }
        }
        binding.apply {
            dec.setOnClickListener {
                if (counter != 1) {
                    counter--
                    binding.amount.setText(counter.toString())
                    binding.sum.setText("${numberFormat.format(counter * price)} UZS")
                }
            }
            inc.setOnClickListener {
                counter++
                binding.amount.setText(counter.toString())
                binding.sum.setText("${numberFormat.format(counter * price)} UZS")
            }
        }


    }

    fun showSnackbar(view: ViewGroup, message: String, backC: Int) {
        val anchorView: View = binding.name
        val inflater = LayoutInflater.from(requireContext())
        val customSnackbarView = inflater.inflate(R.layout.custom_snackbar_layout, view, false)
        val messageTextView: TextView = customSnackbarView.findViewById(R.id.message_textview)
        val back: FrameLayout = customSnackbarView.findViewById(R.id.background)
        messageTextView.text = message
        back.setBackgroundColor(backC)

        val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
        (snackbar.view as Snackbar.SnackbarLayout).removeAllViews()
        (snackbar.view as Snackbar.SnackbarLayout).addView(customSnackbarView)
        snackbar.anchorView = anchorView
        snackbar.show()
    }

}
