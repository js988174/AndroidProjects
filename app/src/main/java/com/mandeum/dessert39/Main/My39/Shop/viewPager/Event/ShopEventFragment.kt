package com.mandeum.dessert39.Main.My39.Shop.viewPager.Event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.Shop.viewPager.Story.ShopStoryAdapter
import com.mandeum.dessert39.Main.My39.Shop.viewPager.Story.ShopStoryModel
import com.mandeum.dessert39.Main.Order.dialog.CouponFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentShopEventBinding


class ShopEventFragment : Fragment() {

    private var _binding: FragmentShopEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShopEventBinding.inflate(layoutInflater)

        binding.couponLayout.setOnClickListener {
            val coupon =  CouponFragment()
            coupon.isCancelable = false
            activity?.supportFragmentManager?.let { coupon.show(it, coupon.tag) }
        }

        val eventModel: ArrayList<ShopEventModel> = ArrayList()
        val rvAdapter : ShopEventAdapter = ShopEventAdapter(eventModel, requireContext())
        val rv : RecyclerView = binding.eventRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        eventModel.add(
            ShopEventModel(" https://ifh.cc/g/dBgxVz.png","http://dessert39.com/data/product/21.png", "달고나", "Dalgona",
                "4.800", "")
        )

        eventModel.add(
            ShopEventModel(" https://ifh.cc/g/dBgxVz.png","http://dessert39.com/data/product/21.png", "달고나", "Dalgona",
                "4.800", "5.000")
        )

        eventModel.add(
            ShopEventModel(" https://ifh.cc/g/dBgxVz.png","http://dessert39.com/data/product/21.png", "달고나", "Dalgona",
                "4.800", "5.000")
        )

        eventModel.add(
            ShopEventModel(" https://ifh.cc/g/dBgxVz.png","http://dessert39.com/data/product/21.png", "달고나", "Dalgona",
                "4.800", "5.000")
        )

        eventModel.add(
            ShopEventModel(" https://ifh.cc/g/dBgxVz.png","http://dessert39.com/data/product/21.png", "달고나", "Dalgona",
                "4.800", "5.000")
        )

        eventModel.add(
            ShopEventModel(" https://ifh.cc/g/dBgxVz.png","http://dessert39.com/data/product/21.png", "달고나", "Dalgona",
                "4.800", "5.000")
        )

        eventModel.add(
            ShopEventModel(" https://ifh.cc/g/dBgxVz.png","http://dessert39.com/data/product/21.png", "달고나", "Dalgona",
                "4.800", "5.000")
        )

        eventModel.add(
            ShopEventModel(" https://ifh.cc/g/dBgxVz.png","http://dessert39.com/data/product/21.png", "달고나", "Dalgona",
                "4.800", "5.000")
        )


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}