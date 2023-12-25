package com.skipissue.maxway.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skipissue.maxway.presentation.fragments.ActiveOrderFragment
import com.skipissue.maxway.presentation.fragments.BasketFragment
import com.skipissue.maxway.presentation.fragments.FinishedOrderFragment
import com.skipissue.maxway.presentation.fragments.MainFragment
import com.skipissue.maxway.presentation.fragments.OrdersFragment
import com.skipissue.maxway.presentation.fragments.ProfileFragment

class HomeViewPagerAdapter (fm: Fragment) : FragmentStateAdapter(fm) {
    override fun getItemCount()=4

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return MainFragment()
            1 -> return BasketFragment()
            2 -> return OrdersFragment()
            3 -> return ProfileFragment()
        }
        return MainFragment()
    }

}