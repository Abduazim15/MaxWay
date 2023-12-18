package com.skipissue.maxway.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skipissue.maxway.presentation.fragments.ActiveOrderFragment
import com.skipissue.maxway.presentation.fragments.FinishedOrderFragment

class ViewPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    override fun getItemCount()=2

    override fun createFragment(position: Int): Fragment {
        when (position){
            1 -> return ActiveOrderFragment()
            2 -> return FinishedOrderFragment()
        }
        return ActiveOrderFragment()
    }

}
