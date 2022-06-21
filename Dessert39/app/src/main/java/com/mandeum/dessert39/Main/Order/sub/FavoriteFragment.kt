package com.mandeum.dessert39.Main.Order.sub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Order.MenuListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.databinding.FragmentFavoriteBinding
import kotlin.concurrent.thread


class FavoriteFragment : Fragment() {

       private var _binding: FragmentFavoriteBinding? = null
       private val binding get() = _binding!!
       lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        thread(start=true) {
            val menuListModel: MenuListModel = ServerApi.menuList(token.toString(), 10)
            if (menuListModel.connection) {
                thread.runOnUiThread {
                    val rv: RecyclerView = binding.favoriteRecyclerView
                    rv.adapter = OrderMenuAdapter(
                        menuItem = menuListModel.list, requireContext()
                    )
                    rv.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}