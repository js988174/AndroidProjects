package com.mandeum.dessert39.Main.My39.Card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.History.OrderHistoryFragment
import com.mandeum.dessert39.Main.My39.History.TermSettingFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentCardCharge2Binding
import com.mandeum.dessert39.databinding.FragmentCardChargeBinding


class CardChargeFragment : Fragment() {

    private var _binding: FragmentCardCharge2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardCharge2Binding.inflate(layoutInflater)


        val cardHistoryModel : ArrayList<CardHistoryModel> = ArrayList()
        val rvAdapter = CardHistoryAdapter(requireContext(), cardHistoryModel)
        val rv : RecyclerView = binding.cardRecyclerView

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

        binding.termBtn.setOnClickListener {
            TermSettingFragment().apply {
                show(this@CardChargeFragment.parentFragmentManager, null)
            }
        }

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        cardHistoryModel.add(CardHistoryModel(R.drawable.card_normal, "일반 충전", "2021.10.10", "20,000", ""))

        cardHistoryModel.add(CardHistoryModel(R.drawable.card_auto, "자동 충전", "2021.11.10", "20,000", ""))

        cardHistoryModel.add(CardHistoryModel(R.drawable.card_cancel, "자동 충전 취소", "2021.10.10", "", "30,000"))


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}