package com.mandeum.dessert39.Main.Order.selectShop.select

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mandeum.dessert39.Main.Order.sub.*

class SelectViewPager(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList = listOf<Fragment>(
        NearShopFragment(),
        AllShopFragment(),
        FrequentShopFragment()

    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}