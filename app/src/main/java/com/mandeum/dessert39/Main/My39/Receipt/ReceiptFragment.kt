package com.mandeum.dessert39.Main.My39.Receipt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.Card.CardHistoryAdapter
import com.mandeum.dessert39.Main.My39.Card.CardHistoryModel
import com.mandeum.dessert39.Main.My39.History.OrderHistoryFragment
import com.mandeum.dessert39.Main.My39.History.TermSettingFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentReceiptBinding


class ReceiptFragment : Fragment() {

    private var _binding: FragmentReceiptBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentReceiptBinding.inflate(layoutInflater)

        binding.termBtn.setOnClickListener {
            TermSettingFragment().apply {
                show(this@ReceiptFragment.parentFragmentManager, null)
            }
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

        val receiptModel : ArrayList<ReceiptModel> = ArrayList()
        val rvAdapter = ReceiptAdapter(requireContext(), receiptModel)
        val rv : RecyclerView = binding.receiptRecyclerView

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        receiptModel.add(ReceiptModel("일반 충전", "2021.10.10", "결제", "10"))

        receiptModel.add(ReceiptModel("자동 충전", "2021.11.10", "충전", "1,000"))

        receiptModel.add(ReceiptModel( "자동 충전", "2021.10.10", "test", "300,000"))


       return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}