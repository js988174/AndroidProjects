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
import com.mandeum.dessert39.Login.ServerApi.Model.MenuListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {

       private var _binding: FragmentFavoriteBinding? = null
       private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)

//        val menuItem: ArrayList<OrderMenuModel> = ArrayList()
//        val rvAdapter : OrderMenuAdapter = OrderMenuAdapter(menuItem, requireContext())
//        val rv : RecyclerView = binding.favoriteRecyclerView
//        rv.adapter = rvAdapter
//        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val menuListModel: MenuListModel = ServerApi.menuList(10)
        if (menuListModel.connection) {
            val rv : RecyclerView = binding.favoriteRecyclerView
            rv.adapter = OrderMenuAdapter(
                menuItem = menuListModel.list, requireContext()
            )
            rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}