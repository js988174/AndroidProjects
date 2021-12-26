package com.mandeum.dessert39.Main.Order.sub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.MenuListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.databinding.FragmentAidBinding
import kotlinx.android.synthetic.main.fragment_aid.*


class AidFragment : Fragment() {

       private var _binding: FragmentAidBinding? = null
       private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAidBinding.inflate(layoutInflater)

//        val menuItem: ArrayList<OrderMenuModel> = ArrayList()

//        val rvAdapter : OrderMenuAdapter = OrderMenuAdapter(menuItem, requireContext())
//        val rv : RecyclerView = binding.aidRecyclerView
//        rv.adapter = rvAdapter
//        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val menuListModel: MenuListModel = ServerApi.menuList(6)
        if (menuListModel.connection) {
            val rv : RecyclerView = binding.aidRecyclerView
            rv.adapter = OrderMenuAdapter(
                menuItem = menuListModel.list, requireContext()
            )
            rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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