package com.mandeum.dessert39.Main.My39.Shop.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mandeum.dessert39.Main.My39.Shop.viewPager.Event.ShopEventFragment
import com.mandeum.dessert39.Main.My39.Shop.viewPager.Information.ShopInformationFragment
import com.mandeum.dessert39.Main.My39.Shop.viewPager.Story.ShopStoryFragment

class ShopViewPager(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList = listOf<Fragment>(
        ShopInformationFragment(),
        ShopStoryFragment(),
        ShopEventFragment(),
        ShopStarFragment()
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}