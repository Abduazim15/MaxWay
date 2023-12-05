package com.skipissue.maxway.presentation.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.MainFragmentBinding
import com.skipissue.maxway.domain.entity.CategoryEntity
import com.skipissue.maxway.domain.entity.FoodEntity
import com.skipissue.maxway.domain.entity.TabEntity
import com.skipissue.maxway.presentation.adapter.CategoriesAdapter
import com.skipissue.maxway.presentation.adapter.TabsAdapter

class MainFragment : Fragment(R.layout.main_fragment) {
    private val binding: MainFragmentBinding by viewBinding()
    private val adapter by lazy { CategoriesAdapter() }
    private val rootView by lazy { requireView() }
    private val tabAdapter by lazy { TabsAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = Color.WHITE
        binding.apply {
            tab.adapter = tabAdapter
            recycler.adapter = adapter
            search.addTextChangedListener { text ->
                if (text?.toString().isNullOrEmpty())
                    clear.visibility = View.INVISIBLE
                else if (clear.visibility == View.INVISIBLE)
                    clear.visibility = View.VISIBLE
                else {
                }
            }
            clear.setOnClickListener {
                search.setText("")
            }
        }
        adapter.submitList(
            listOf(
                CategoryEntity(
                    0, "Burgers", listOf(
                        FoodEntity(1, "Burger", "25000"),
                        FoodEntity(2, "Cheese Burger", "28000"),
                        FoodEntity(3, "Max Burger", "20000"),
                        FoodEntity(4, "Max Cheese Burger", "35000"),
                    )
                ),
                CategoryEntity(
                    1, "Lavash", listOf(
                        FoodEntity(1, "Burger", "25000"),
                        FoodEntity(2, "Cheese Burger", "28000"),
                        FoodEntity(3, "Max Burger", "20000"),
                    )
                ),
                CategoryEntity(
                    2, "Hot-Dog", listOf(
                        FoodEntity(1, "Burger", "25000"),
                        FoodEntity(2, "Cheese Burger", "28000"),
                        FoodEntity(3, "Max Burger", "20000"),
                        FoodEntity(4, "Max Cheese Burger", "35000"),
                    )
                ),
            )
        )
        tabAdapter.submitList(
            adapter.currentList.map { it -> TabEntity(it.id, it.name) }
        )

        val layoutManager = binding.recycler.layoutManager as LinearLayoutManager
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()


                if (firstVisibleItemPosition != null) {

                }
            }
        })
        //To prevent moving BtmNav above
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility = View.VISIBLE
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            val isKeyboardVisible = keypadHeight > screenHeight * 0.15

            if (isKeyboardVisible) {
                // Keyboard is open
                requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility =
                    View.GONE
            } else {
                // Keyboard is closed
                requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility =
                    View.VISIBLE
            }
        }
    }
}
