package com.mandeum.dessert39.Main.My39.Event

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardEventModel
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.My39.Sound.InquiriesAdapter
import com.mandeum.dessert39.Main.My39.Sound.InquiriesItem
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentEventBinding
import kotlin.concurrent.thread


class EventFragment : Fragment() {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventFragment_to_orderFragment)
        }

        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventFragment_to_homeFragment)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventFragment_to_alarmFragment)
        }



        thread(start = true) {
            val boardListModel: BoardEventModel = ServerApi.BoardEvent(token.toString(), 1)
            thread.runOnUiThread {
                if (boardListModel.connection) {
                    val rv : RecyclerView = binding.eventRecyclerView
                    rv.adapter = EventAdapter(requireContext(), boardListModel.list)
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