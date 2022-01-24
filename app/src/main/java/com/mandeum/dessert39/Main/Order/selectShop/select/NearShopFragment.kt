package com.mandeum.dessert39.Main.Order.selectShop.select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAidBinding
import com.mandeum.dessert39.databinding.FragmentNearShopBinding
import com.mandeum.dessert39.databinding.FragmentOrderSelectShopBinding


class NearShopFragment : Fragment() {

    private var _binding: FragmentNearShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNearShopBinding.inflate(layoutInflater)

        val selectShopModel: ArrayList<SelectShopModel> = ArrayList()

        val rvAdapter : SelectShopAdapter = SelectShopAdapter(selectShopModel, requireContext())
        val rv : RecyclerView = binding.nearRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}