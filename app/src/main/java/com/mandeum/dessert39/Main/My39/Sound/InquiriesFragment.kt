package com.mandeum.dessert39.Main.My39.Sound

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.My39.Receipt.ReceiptAdapter
import com.mandeum.dessert39.Main.My39.Receipt.ReceiptModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentInquiriesBinding
import kotlin.concurrent.thread


class InquiriesFragment : Fragment() {

    private var _binding: FragmentInquiriesBinding? = null
    private val binding get() = _binding!!
    lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInquiriesBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        thread(start = true) {
            val boardListModel: BoardListModel = ServerApi.BoardList(token.toString(), 1)
            thread.runOnUiThread {
                if (boardListModel.connection) {
                    val rv: RecyclerView = binding.inquiriesRecyclerView
                    rv.adapter = InquiriesAdapter(requireContext(), boardListModel.list)
                    rv.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}