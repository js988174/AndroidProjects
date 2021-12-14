package com.mandeum.dessert39.Main.My39.Question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding ? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_questionFragment_to_orderFragment)
        }

        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_questionFragment_to_homeFragment)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_questionFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_questionFragment_to_alarmFragment)
        }


        val questionItem: ArrayList<QuestionItem> = ArrayList()

        val rv : RecyclerView = binding.questionRecyclerView



        val tabItem = ArrayList<String>()
        tabItem.add("전체")
        tabItem.add("회원가입")
        tabItem.add("결제")
        tabItem.add("포인트")
        tabItem.add("쿠폰")
        tabItem.add("주문")
        tabItem.add("알림")
        tabItem.add("충전")

        val tabRecyclerView : RecyclerView = binding.questionTabRecyclerView
        val tabAdapter = QuestionTabAdapter(requireContext(), tabItem, questionItem, rv,this)
        tabRecyclerView.adapter = tabAdapter
        tabRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        questionItem.add(QuestionItem("회원가입","이벤트1","test test test test test test test", isExpanded = false))
        questionItem.add(QuestionItem("결제","이벤트1","test test test test test test test", isExpanded = false))
        questionItem.add(QuestionItem("포인트","이벤트1","test test test test test test test", isExpanded = false))
        questionItem.add(QuestionItem("쿠폰","이벤트1","test test test test test test test", isExpanded = false))
        questionItem.add(QuestionItem("주문","이벤트1","test test test test test test test", isExpanded = false))
        questionItem.add(QuestionItem("알림","이벤트1","test test test test test test test", isExpanded = false))
        questionItem.add(QuestionItem("충전","이벤트1","test test test test test test test", isExpanded = false))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}