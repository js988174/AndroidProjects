package com.mandeum.dessert39.Main.My39.Event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.Sound.InquiriesAdapter
import com.mandeum.dessert39.Main.My39.Sound.InquiriesItem
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentEventBinding


class EventFragment : Fragment() {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEventBinding.inflate(layoutInflater)

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


        val eventItem : ArrayList<EventItem> = ArrayList()
        val rvAdapter = EventAdapter(requireContext(), eventItem)
        val rv : RecyclerView = binding.eventRecyclerView

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        eventItem.add(EventItem(term = false, "[진행중]", "1월 이벤트 진행중입니다.","2021.10.01","2021.12.30","이벤트 내용입니다.","https://ifh.cc/g/CJtiuO.png"))

        eventItem.add(EventItem(term = false, "[진행중]", "1월 이벤트 진행중입니다.","2021.10.01","2021.12.30","이벤트 내용입니다.","https://ifh.cc/g/CJtiuO.png"))

        eventItem.add(EventItem(term = true, "[종료중]", "1월 이벤트 진행중입니다.","2021.10.01","2021.12.30","이벤트 내용입니다.",""))


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}