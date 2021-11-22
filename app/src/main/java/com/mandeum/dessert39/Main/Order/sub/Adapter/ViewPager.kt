package com.mandeum.dessert39.Main.Order.sub.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mandeum.dessert39.Main.Order.sub.*

class ViewPager(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList = listOf<Fragment>(
        RecommendFragment(),
        DessertFragment(),
        CoffeeFragment(),
        NonCoffeeFragment(),
        MilkTeaFragment(),
        BlendingFragment(),
        AidFragment(),
        FrappeFragment(),
        FromageFragment(),
        IceFragment(),
        FavoriteFragment()

    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}