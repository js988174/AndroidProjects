package com.mandeum.dessert39.Main.Order.sub

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {

    lateinit var binding : FragmentFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        val menuItem: ArrayList<OrderMenuModel> = ArrayList()
        val rvAdapter : OrderMenuAdapter = OrderMenuAdapter(menuItem)
        val rv : RecyclerView = binding.favoriteRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


      val fa1 =  requireActivity().getSharedPreferences("shopping_cart", Context.MODE_PRIVATE).getString("cart_title", "default_value")

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = true))

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = true))

        menuItem.add(OrderMenuModel("","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = true))


        return binding.root
    }


}