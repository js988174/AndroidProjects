package com.mandeum.dessert39.Main.My39.Board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.Point.PointAdapter
import com.mandeum.dessert39.Main.My39.Point.PointModel
import com.mandeum.dessert39.Main.Order.OrderFragment
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentBoardBinding


class BoardFragment : Fragment() {

    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoardBinding.inflate(layoutInflater)

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
        val rv : RecyclerView = binding.boardRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        boardItem.add(BoardItem("[긴급]","이벤트","2021.10.10","test test test test test test test test test test  test  test test test test test test testtest test test test test test test test test","https://ifh.cc/g/CJtiuO.png"))


        boardItem.add(BoardItem("[긴급]","이벤트","2021.10.10","test test test test test test test test test teest test test test test test testtest test test test test test test test testtest test test test test test test test testtest test test test test test test test test","https://ifh.cc/g/CJtiuO.png"))

        boardItem.add(BoardItem("긴급","이벤트","2021.10.10","test test test test test test test tesst test test testst test test test testtest test test test test test test test testtest test test test test test test test test","https://ifh.cc/g/CJtiuO.png"))

        boardItem.add(BoardItem("긴급","이벤트","2021.10.10","test test test test test test test test test tesst test test test test test test test test test test testtest test test test test test test test test","https://ifh.cc/g/CJtiuO.png"))

        boardItem.add(BoardItem("긴급","이벤트","2021.10.10","test test test test test test ttest test test test test test test test testtest test test test test test test test testtest test test test test test test test testtest test test test test test test test testtestst test test test test test", ""))
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}