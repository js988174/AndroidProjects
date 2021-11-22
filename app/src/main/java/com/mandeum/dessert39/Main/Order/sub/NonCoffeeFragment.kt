package com.mandeum.dessert39.Main.Order.sub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.databinding.FragmentNonCoffeeBinding


class NonCoffeeFragment : Fragment() {

    lateinit var binding : FragmentNonCoffeeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNonCoffeeBinding.inflate(layoutInflater)

        val menuItem: ArrayList<OrderMenuModel> = ArrayList()

        val rvAdapter1 : OrderMenuAdapter = OrderMenuAdapter(menuItem)
        val rv : RecyclerView = binding.nonCoffeeRecyclerView
        rv.adapter = rvAdapter1
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = true, favorites = false))


        menuItem.add(OrderMenuModel("","https://ifh.cc/g/DlbrKZ.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))


        menuItem.add(OrderMenuModel("","https://ifh.cc/g/DlbrKZ.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))


        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))


        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))


        return binding.root
    }


}