package com.mandeum.dessert39.Main.Order.sub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.MenuListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.databinding.FragmentCoffeeBinding


class CoffeeFragment : Fragment() {

       private var _binding: FragmentCoffeeBinding? = null
       private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoffeeBinding.inflate(layoutInflater)

//        val menuItem: ArrayList<OrderMenuModel> = ArrayList()
//        val rvAdapter : OrderMenuAdapter = OrderMenuAdapter(menuItem, requireContext())

        val menuListModel: MenuListModel = ServerApi.menuList(2)
        if (menuListModel.connection) {
            val rv : RecyclerView = binding.coffeeRecyclerView
            rv.adapter = OrderMenuAdapter(
                menuItem = menuListModel.list, requireContext()
            )
            rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

//        menuItem.add(OrderMenuModel(1,"","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//            , "6.800", soldOut = false, favorites = false))
//
//        menuItem.add(OrderMenuModel(2,"","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//            , "6.800", soldOut = true, favorites = false))
//
//
//        menuItem.add(OrderMenuModel(3,"","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//            , "6.800", soldOut = true, favorites = false))



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}