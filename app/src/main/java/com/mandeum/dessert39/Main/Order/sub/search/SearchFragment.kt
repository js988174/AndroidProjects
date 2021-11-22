package com.mandeum.dessert39.Main.Order.sub.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderSearchAdapter
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_orderFragment)
        }

        val menuItem: ArrayList<OrderMenuModel> = ArrayList()

        val rvAdapter : OrderSearchAdapter = OrderSearchAdapter(menuItem)
        val rv : RecyclerView = binding.searchRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))



        return binding.root
    }


}