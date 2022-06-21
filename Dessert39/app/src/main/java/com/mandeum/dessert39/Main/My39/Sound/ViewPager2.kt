package com.mandeum.dessert39.Main.My39.Sound

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mandeum.dessert39.Main.Order.sub.*

class ViewPager2(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList = listOf<Fragment>(
        ContactFragment(),
        InquiriesFragment()

    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}