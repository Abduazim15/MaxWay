package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.MainFragmentBinding
import com.skipissue.maxway.domain.entity.CategoryEntity
import com.skipissue.maxway.domain.entity.FoodEntity
import com.skipissue.maxway.presentation.adapter.CategoriesAdapter

class MainFragment : Fragment(R.layout.main_fragment) {
    private val binding : MainFragmentBinding by viewBinding()
    private val adapter by lazy { CategoriesAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recycler.adapter = adapter
        adapter.submitList(listOf(
            CategoryEntity(0, "Burgers", listOf(
                FoodEntity(1, "Burger", "25000"),
                FoodEntity(2, "Cheese Burger", "28000"),
                FoodEntity(3, "Max Burger", "20000"),
                FoodEntity(4, "Max Cheese Burger", "35000"),
            )),
            CategoryEntity(1, "Lavash", listOf(
                FoodEntity(1, "Burger", "25000"),
                FoodEntity(2, "Cheese Burger", "28000"),
                FoodEntity(3, "Max Burger", "20000"),
                FoodEntity(4, "Max Cheese Burger", "35000"),
            )),
            CategoryEntity(2, "Hot-Dog", listOf(
                FoodEntity(1, "Burger", "25000"),
                FoodEntity(2, "Cheese Burger", "28000"),
                FoodEntity(3, "Max Burger", "20000"),
                FoodEntity(4, "Max Cheese Burger", "35000"),
            )),
        ))
    }
}
