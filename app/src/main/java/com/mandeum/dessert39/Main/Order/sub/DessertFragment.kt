package com.mandeum.dessert39.Main.Order.sub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.CategoryModel
import com.mandeum.dessert39.Login.ServerApi.Model.DessertListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuModel
import com.mandeum.dessert39.databinding.FragmentDessertBinding


class DessertFragment : Fragment() {

       private var _binding: FragmentDessertBinding? = null
       private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentDessertBinding.inflate(layoutInflater)

        val menuItem: ArrayList<OrderMenuModel> = ArrayList()
        val rvAdapter = OrderMenuAdapter(menuItem, requireContext())

        val dessert : RecyclerView = binding.dessertRecyclerView

//        rv.adapter = rvAdapter
//
//        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)



        val subMenuItem: ArrayList<SubMenuModel> = ArrayList()

        subMenuItem.add(SubMenuModel("케이크", 1,select = true))

        subMenuItem.add(SubMenuModel("홀케이크",2, select = false))

        subMenuItem.add(SubMenuModel("베이커리",3, select = false))

        subMenuItem.add(SubMenuModel("도쿄롤",4, select = false))

        subMenuItem.add(SubMenuModel("오믈렛",5, select = false))

        subMenuItem.add(SubMenuModel("마카롱",6, select = false))


        val sub_menu_recyclerView: RecyclerView = binding.subMenuRecyclerView
//        val adapter = SubMenuAdapter(requireContext(), subMenuItem, menuItem, dessert)
//        sub_menu_recyclerView.adapter = adapter
//        sub_menu_recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val categoryModel: CategoryModel = ServerApi.Category()
        if (categoryModel.connection) {
            if (categoryModel.errCode == "0000") {
                val dessertListModel: DessertListModel = ServerApi.dessertList()
                if (dessertListModel.errCode == "0000") {
                    sub_menu_recyclerView.adapter = SubMenuAdapter(
                        requireContext(), subMenuItem = categoryModel.list, menuItem = dessertListModel.list, dessert)
                    sub_menu_recyclerView.layoutManager =
                        LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                }
            }
        } else {
            Toast.makeText(requireContext(), "${categoryModel.errCode}",
                Toast.LENGTH_SHORT
            ).show()
        }



//        menuItem.add(OrderMenuModel(1,"케이크","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//        , "6.800", soldOut = false, favorites = false))
//
//        menuItem.add(OrderMenuModel(2,"홀케이크","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//            , "6.800", soldOut = true, favorites = false))
//
//
//        menuItem.add(OrderMenuModel(3,"도쿄롤","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//            , "6.800", soldOut = false, favorites = false))
//
//
//        menuItem.add(OrderMenuModel(4,"오믈렛","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//            , "6.800", soldOut = false, favorites = false))
//
//        menuItem.add(OrderMenuModel(5,"마카롱","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//            , "6.800", soldOut = false, favorites = false))
//
//
//        menuItem.add(OrderMenuModel(6,"마카롱","https://ifh.cc/g/RwgS7v.png","달고나 초코라떼 아이스", "Dalgona"
//            , "6.800", soldOut = false, favorites = false))




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}