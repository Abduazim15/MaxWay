package com.skipissue.maxway.presentation.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.AboutOrderFragmentBinding
import com.skipissue.maxway.presentation.adapter.OrdersAdapter
import com.skipissue.maxway.presentation.viewmodels.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BillFragment : Fragment(R.layout.about_order_fragment) {
    private var id : String? = null
    private val viewModel: HistoryViewModel by viewModels()
    private val adapter by lazy { OrdersAdapter() }
    private val binding: AboutOrderFragmentBinding by viewBinding()
    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).hideOrShow(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).hideOrShow(false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        id = arguments?.getString("id")
        viewModel.getAboutOrder(id!!)
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.stateSuccessD.collect {data ->
                adapter.submitList(data.steps[0].products)
                binding.apply {
                    status.setText(if (data.paid) {"Muvaffaqiyatli"} else {
                        status.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#BCF44336"))
                        status.setTextColor(Color.BLACK)
                        "Buyurtma bekor qilindi"})
                    location.setText(data.to_address)
                    title.setText("Buyurtma No " + data.external_order_id)
                    delivery.setText(data.co_delivery_price.toString() + " so'm")
                    orderId.setText("No " + data.external_order_id)
                    total.setText(data.payment[0].paid_amount.toString() + " so'm")
                    val createdAt = data.created_at.substring(0, data.created_at.indexOf(" "))
                    val timeAt = data.created_at.substring(data.created_at.indexOf(" "))
                    date.setText(createdAt.replace("-", "."))
                    time.setText(timeAt)
                }
            }
        }
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

}
