package com.mandeum.dessert39.Main.My39.Board

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardEvent2Model
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardEventModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.My39.Event.EventAdapter
import com.mandeum.dessert39.Main.My39.Point.PointAdapter
import com.mandeum.dessert39.Main.My39.Point.PointModel
import com.mandeum.dessert39.Main.Order.OrderFragment
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentBoardBinding
import kotlin.concurrent.thread


class BoardFragment : Fragment() {

    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!
    lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoardBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardFragment_to_orderFragment)
        }

        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardFragment_to_alarmFragment)
        }




        val boardItem: ArrayList<BoardItem> = ArrayList()
        val rvAdapter = BoardAdapter(requireContext(), boardItem)


        thread(start = true) {
            val boardListModel: BoardEvent2Model = ServerApi.BoardEvent2(token.toString(), 1)
            thread.runOnUiThread {
                if (boardListModel.connection) {
                    val rv : RecyclerView = binding.boardRecyclerView
                    rv.adapter = BoardAdapter(requireContext(), boardListModel.list)
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