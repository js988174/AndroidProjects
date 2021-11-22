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
import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuModel
import com.mandeum.dessert39.databinding.FragmentDessertBinding
import kotlinx.android.synthetic.main.fragment_dessert.*


class DessertFragment : Fragment() {

    lateinit var binding : FragmentDessertBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentDessertBinding.inflate(layoutInflater)

        val menuItem: ArrayList<OrderMenuModel> = ArrayList()
        val rvAdapter = OrderMenuAdapter(menuItem)

        val rv : RecyclerView = binding.dessertRecyclerView

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val subMenuItem: ArrayList<SubMenuModel> = ArrayList()

        subMenuItem.add(SubMenuModel("케이크", select = true))

        subMenuItem.add(SubMenuModel("홀케이크", select = false))

        subMenuItem.add(SubMenuModel("도쿄롤", select = false))

        subMenuItem.add(SubMenuModel("오믈렛", select = false))

        subMenuItem.add(SubMenuModel("마카롱", select = false))


        menuItem.add(OrderMenuModel("케이크","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
        , "6.800", soldOut = false, favorites = false))

        menuItem.add(OrderMenuModel("홀케이크","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = true, favorites = false))


        menuItem.add(OrderMenuModel("도쿄롤","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))


        menuItem.add(OrderMenuModel("오믈렛","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))

        menuItem.add(OrderMenuModel("마카롱","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))


        menuItem.add(OrderMenuModel("마카롱","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
            , "6.800", soldOut = false, favorites = false))


        val sub_menu_recyclerView: RecyclerView = binding.subMenuRecyclerView
        val adapter = SubMenuAdapter(requireContext(), subMenuItem, menuItem, rv)
        sub_menu_recyclerView.adapter = adapter
        sub_menu_recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        return binding.root
    }


}