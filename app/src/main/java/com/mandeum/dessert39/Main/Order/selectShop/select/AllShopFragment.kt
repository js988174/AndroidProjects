package com.mandeum.dessert39.Main.Order.selectShop.select

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Order.MenuListModel
import com.mandeum.dessert39.Login.ServerApi.Model.Order.OrderShopMenuModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAllShopBinding
import com.mandeum.dessert39.databinding.FragmentNearShopBinding
import kotlin.concurrent.thread


class AllShopFragment : Fragment() {

    private  var _binding: FragmentAllShopBinding? = null
    private val binding get() = _binding!!
    lateinit var thread : HomeActivity
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAllShopBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")


        val selectShopModel: ArrayList<SelectShopModel> = ArrayList()




        thread(start = true) {
            val shopList: OrderShopMenuModel = ServerApi.AllStore(token = token.toString(), latitude=latitude , longitude = longitude)
            if (shopList.connection) {
                thread.runOnUiThread {
                    val rv: RecyclerView = binding.allRecyclerView
                    rv.adapter = SelectShopAdapter(
                        selectShopModel = shopList.list, requireContext())
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