package com.mandeum.dessert39.Main.My39.Shop

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mandeum.dessert39.Main.Order.sub.*

class My39ShopViewPager(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList = listOf<Fragment>(
        My39NearShopFragment(),
        My39AllshopFragment(),
        My39FrequentShopFragment()

    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}