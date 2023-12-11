package com.skipissue.maxway.presentation.fragments

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.MainFragmentBinding
import com.skipissue.maxway.domain.entity.TabEntity
import com.skipissue.maxway.presentation.adapter.CategoriesAdapter
import com.skipissue.maxway.presentation.adapter.TabsAdapter
import com.skipissue.maxway.presentation.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {
    private val binding: MainFragmentBinding by viewBinding()
    private val adapter by lazy { CategoriesAdapter() }
    private val rootView by lazy { requireView() }
    private val tabs by lazy { ArrayList<TabEntity>()}
    private val tabAdapter by lazy { TabsAdapter(tabs) }
    private var isUserScrolling = false
    private val viewModel: ProductsViewModel by viewModels()
    private val smoothScroller by lazy {object : LinearSmoothScroller(requireContext()) {
        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = Color.WHITE
        lifecycleScope.launch {
            viewModel.stateSuccess.collect{data ->
                adapter.submitList(data.categories)
                tabs.clear()
                tabs.addAll(
                    adapter.currentList.map { it -> TabEntity(it.id, it.title.uz) }
                )
                tabAdapter.notifyDataSetChanged()
            }
        }
        val layoutManagerR = LinearLayoutManager(requireContext())
        binding.apply {
            tab.adapter = tabAdapter
            recycler.adapter = adapter
            recycler.layoutManager = layoutManagerR
            search.addTextChangedListener { text ->
                if (text?.toString().isNullOrEmpty())
                    clear.visibility = View.INVISIBLE
                else if (clear.visibility == View.INVISIBLE)
                    clear.visibility = View.VISIBLE
            }
            clear.setOnClickListener {
                search.setText("")
            }
            val layoutManager = recycler.layoutManager as LinearLayoutManager
            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            if (isUserScrolling) {
                                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                                if (firstVisibleItemPosition != RecyclerView.NO_POSITION) {
                                    tabAdapter.select(firstVisibleItemPosition)
                                    binding.tab.smoothScrollToPosition(firstVisibleItemPosition)
                                }
                            }
                            isUserScrolling = false
                        }
                        RecyclerView.SCROLL_STATE_DRAGGING, RecyclerView.SCROLL_STATE_SETTLING -> {
                            isUserScrolling = true
                        }
                    }
                }
            })
        }
        adapter.setOnClickClickListener { cIndex, index ->
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility = View.GONE
            parentFragmentManager.beginTransaction().setReorderingAllowed(true).addToBackStack("MainFragment").replace(R.id.container, ChooseFragment::class.java, bundleOf(
                "id" to adapter.currentList[cIndex].products[index].id
            )).commit()
        }
        tabAdapter.setOnClickClickListener { index ->
            if (index == tabAdapter.list.size-1) {
                binding.recycler.smoothScrollToPosition(index)
                isUserScrolling = false
            }
            else {
                smoothScroller.targetPosition = index
                layoutManagerR.startSmoothScroll(smoothScroller)
            }
        }
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility = View.VISIBLE
    }
}