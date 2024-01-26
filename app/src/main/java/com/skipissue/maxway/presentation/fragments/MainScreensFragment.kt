package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.databinding.ViewpagerFragmentBinding
import com.skipissue.maxway.presentation.adapter.HomeViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainScreensFragment : Fragment(R.layout.viewpager_fragment) {
    private val binding: ViewpagerFragmentBinding by viewBinding()
    private lateinit var adapter: HomeViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var bottom: BottomNavigationView

    @Inject
    lateinit var settings: Settings
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = HomeViewPagerAdapter(this )
        viewPager = binding.viewPager
        if (viewPager.adapter == null)
            binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        bottom = (requireActivity() as MainActivity).bottom
        bottom.visibility = View.VISIBLE
        viewPager.setCurrentItem(when (bottom.selectedItemId) {
            R.id.main -> 0
            R.id.basket -> 1
            R.id.my_orders -> 2
            R.id.profile -> 3
            else -> 0
        }, false)
        bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> {
                    viewPager.setCurrentItem(0, false)
                }

                R.id.basket -> {
                    if (settings.accessToken.isNullOrEmpty()) {
                        parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                            .addToBackStack("MainScreensFragment")
                            .replace(
                                R.id.container,
                                PhoneFragment::class.java,
                                bundleOf("tag" to BasketFragment.TAG)
                            ).commit()
                    } else
                        viewPager.setCurrentItem(1, false)
                }

                R.id.my_orders -> {
                    if (settings.accessToken.isNullOrEmpty()) {
                        parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                            .addToBackStack("MainScreensFragment")
                            .replace(
                                R.id.container,
                                PhoneFragment::class.java,
                                bundleOf("tag" to OrdersFragment.TAG)
                            ).commit()
                    } else
                        viewPager.setCurrentItem(2, false)
                }

                R.id.profile -> {
                    if (settings.accessToken.isNullOrEmpty()) {
                        parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                            .addToBackStack("MainScreensFragment")
                            .replace(
                                R.id.container,
                                PhoneFragment::class.java,
                                bundleOf("tag" to OrdersFragment.TAG)
                            ).commit()
                    } else
                        viewPager.setCurrentItem(3, false)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onResume() {
        super.onResume()
        viewPager.setCurrentItem(when (bottom.selectedItemId) {
            R.id.main -> 0
            R.id.basket -> 1
            R.id.my_orders -> 2
            R.id.profile -> 3
            else -> 0
        }, false)
    }
}