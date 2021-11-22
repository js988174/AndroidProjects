package com.mandeum.dessert39.Main.Order.selectShop.select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAllShopBinding
import com.mandeum.dessert39.databinding.FragmentFrequentShopBinding


class FrequentShopFragment : Fragment() {

    private lateinit var binding: FragmentFrequentShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFrequentShopBinding.inflate(layoutInflater)

        val selectShopModel: ArrayList<SelectShopModel> = ArrayList()

        val rvAdapter : SelectShopAdapter = SelectShopAdapter(selectShopModel, requireContext())
        val rv : RecyclerView = binding.freRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        return binding.root
      }
    }


