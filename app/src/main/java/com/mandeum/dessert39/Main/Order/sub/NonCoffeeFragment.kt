package com.mandeum.dessert39.Main.Order.sub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Order.MenuListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.databinding.FragmentNonCoffeeBinding
import kotlin.concurrent.thread


class NonCoffeeFragment : Fragment() {


       private var _binding: FragmentNonCoffeeBinding? = null
    private val binding get() = _binding!!
    lateinit var thread : HomeActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNonCoffeeBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        thread(start = true) {
            val menuListModel: MenuListModel = ServerApi.menuList(token.toString(),3)

            if (menuListModel.connection) {
                if (menuListModel.errCode == "0000") {
                    thread.runOnUiThread {
                        val rv: RecyclerView = binding.nonCoffeeRecyclerView
                        rv.adapter = OrderMenuAdapter(
                            menuItem = menuListModel.list, requireContext()
                        )
                        rv.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "connection = ${menuListModel.connection}\n연결 실패",
                        Toast.LENGTH_SHORT
                    ).show()
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