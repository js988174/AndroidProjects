package com.mandeum.dessert39.Main.My39.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.DateSelector
import com.mandeum.dessert39.Main.My39.History.OrderHistoryFragment.*
import com.mandeum.dessert39.databinding.FragmentOrderHistoryBinding


class OrderHistoryFragment : Fragment() {

    companion object {

        private var _binding: FragmentOrderHistoryBinding? = null
        private val binding get() = _binding!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrderHistoryBinding.inflate(layoutInflater)


          binding.termBtn.setOnClickListener {
            TermSettingFragment().apply {
                show(this@OrderHistoryFragment.parentFragmentManager, null)
            }
        }

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.imageBtn.setOnClickListener {
            val array: Array<String> = arrayOf("전체", "일반충전", "자동충전", "취소")
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("주문상태")
            builder.setItems(array) { _, i ->
                when (i) {
                    0 -> {

                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                }
            }
            builder.create().show()
        }

        val orderHistoryModel : ArrayList<OrderHistoryModel> = ArrayList()

        val rvAdapter = OrderHistoryAdapter(requireContext(), orderHistoryModel)

        val rv : RecyclerView = binding.orderHistory

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        orderHistoryModel.add(OrderHistoryModel("http://dessert39.com/data/product/21.png",
        "2021.10.01", "달고나 초코라떼 아이스 외2개", "청라호수공원점", status = false))

        orderHistoryModel.add(OrderHistoryModel("http://dessert39.com/data/product/21.png",
            "2021.10.01", "달고나 초코라떼 아이스 외2개", "청라호수공원점", status = false))

        orderHistoryModel.add(OrderHistoryModel("http://dessert39.com/data/product/21.png",
            "2021.10.01", "달고나 초코라떼 아이스 외2개", "청라호수공원점", status = false))

        orderHistoryModel.add(OrderHistoryModel("http://dessert39.com/data/product/21.png",
            "2021.10.01", "달고나 초코라떼 아이스 외2개", "청라호수공원점", status = false))

        orderHistoryModel.add(OrderHistoryModel("http://dessert39.com/data/product/21.png",
            "2021.10.01", "달고나 초코라떼 아이스 외2개", "청라호수공원점", status = true))
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}