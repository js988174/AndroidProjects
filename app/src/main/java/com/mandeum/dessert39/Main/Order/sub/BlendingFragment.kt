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
import com.mandeum.dessert39.databinding.FragmentBlendingBinding
import kotlin.concurrent.thread


class BlendingFragment : Fragment() {

       private var _binding: FragmentBlendingBinding? = null
       private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentBlendingBinding.inflate(layoutInflater)

//        val menuItem: ArrayList<OrderMenuModel> = ArrayList()
//        val rvAdapter : OrderMenuAdapter = OrderMenuAdapter(menuItem, requireContext())
//        val rv : RecyclerView = binding.blendingRecyclerView
//        rv.adapter = rvAdapter
//        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        thread(start = true) {
            val menuListModel: MenuListModel = ServerApi.menuList(5)
            if (menuListModel.connection) {
                val rv: RecyclerView = binding.blendingRecyclerView
                rv.adapter = OrderMenuAdapter(
                    menuItem = menuListModel.list, requireContext()
                )
                rv.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }





        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}