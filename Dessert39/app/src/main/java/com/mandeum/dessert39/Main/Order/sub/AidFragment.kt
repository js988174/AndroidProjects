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
import com.mandeum.dessert39.Intro.MainActivity
import com.mandeum.dessert39.Login.ServerApi.Model.Order.MenuListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.databinding.FragmentAidBinding
import kotlin.concurrent.thread


class AidFragment : Fragment() {

       private var _binding: FragmentAidBinding? = null
       private val binding get() = _binding!!
       lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAidBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

//        val menuItem: ArrayList<OrderMenuModel> = ArrayList()
//        val rvAdapter : OrderMenuAdapter = OrderMenuAdapter(menuItem, requireContext())
//        val rv : RecyclerView = binding.aidRecyclerView
//        rv.adapter = rvAdapter
//        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        thread(start = true) {
            val menuListModel: MenuListModel = ServerApi.menuList(TOKEN = token.toString(), 6)
            if (menuListModel.connection) {
                thread.runOnUiThread {
                    val rv: RecyclerView = binding.aidRecyclerView
                    rv.adapter = OrderMenuAdapter(
                        menuItem = menuListModel.list, requireContext()
                    )
                    rv.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }
        }


//            rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
//                    val itemTotalCount = recyclerView.adapter?.itemCount?.minus(1)
//
//                    if (lastVisibleItemPosition != itemTotalCount) {
//                        binding.emptyView.isGone = false
//                    }
//                }
//            })


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}