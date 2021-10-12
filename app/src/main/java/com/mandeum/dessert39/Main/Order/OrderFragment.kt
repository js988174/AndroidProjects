package com.mandeum.dessert39.Main.Order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.mandeum.dessert39.Main.Order.sub.CoffeeFragment
import com.mandeum.dessert39.Main.Order.sub.DesertFragment
import com.mandeum.dessert39.Main.Order.sub.RecommendFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderBinding


class OrderFragment : Fragment(R.layout.fragment_order) {
    private var _binding : FragmentOrderBinding? = null
    private val binding
    get() = _binding!!

    private val title = arrayOf("추천","디저트","커피")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOrderBinding.bind(view)

        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.pager.apply {
            adapter = object : FragmentStateAdapter(requireActivity()) {
                override fun getItemCount(): Int {
                    return title.size
                }

                override fun createFragment(position: Int): Fragment {
                    return when (position) {
                        0 -> RecommendFragment()
                        1 -> DesertFragment()
                        else -> CoffeeFragment()
                    }
                }
            }
        }
        TabLayoutMediator(binding.tabLayout, binding.pager){tab, position ->
            tab.text = title[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}