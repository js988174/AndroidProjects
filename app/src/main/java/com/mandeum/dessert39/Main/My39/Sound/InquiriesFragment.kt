package com.mandeum.dessert39.Main.My39.Sound

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.Receipt.ReceiptAdapter
import com.mandeum.dessert39.Main.My39.Receipt.ReceiptModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentInquiriesBinding


class InquiriesFragment : Fragment() {

    private var _binding: FragmentInquiriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInquiriesBinding.inflate(layoutInflater)


        val inquiriesItem : ArrayList<InquiriesItem> = ArrayList()
        val rvAdapter = InquiriesAdapter(requireContext(), inquiriesItem)
        val rv : RecyclerView = binding.inquiriesRecyclerView

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        inquiriesItem.add(InquiriesItem(anser = true, "결제", "안녕하세요", "2021.10.10", "test", "test2", "2021.10.10",isExpanded = false, anserExpanded = false))

        inquiriesItem.add(InquiriesItem(anser = false, "결제", "안녕하세요", "2021.10.10", "test", "", "", isExpanded = false, anserExpanded = false))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}