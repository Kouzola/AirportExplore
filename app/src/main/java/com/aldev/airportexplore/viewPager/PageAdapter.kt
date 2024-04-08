package com.aldev.airportexplore.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RunwayInfoPage()
            1 -> MetarInfoPage()
            else -> NavaidsInfoFragment()
        }
    }
}