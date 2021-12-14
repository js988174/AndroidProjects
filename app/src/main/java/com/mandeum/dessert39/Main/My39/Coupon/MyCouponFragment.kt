package com.mandeum.dessert39.Main.My39.Coupon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Order.dialog.CouponModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentCouponBinding
import com.mandeum.dessert39.databinding.FragmentMyCouponBinding


class MyCouponFragment : Fragment() {

    private var _binding: FragmentMyCouponBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMyCouponBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val couponList: ArrayList<MyCouponModel> = ArrayList()

        val rvAdapter = MyCouponAdapter(couponList, requireContext())

        val rv : RecyclerView = binding.couponRecyclerView

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        couponList.add(MyCouponModel(R.drawable.coupon_bg2,"청라호수공원점","아메리카노", "1,000", "5,000원", "2021.10.10","2021.11.30",use = true, expiry = false ))
        couponList.add(MyCouponModel(R.drawable.coupon_gray_bg,"청라호수공원점","아메리카노", "1,000", "5,000원", "2021.10.10","2021.11.30",use = false, expiry = true ))


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}