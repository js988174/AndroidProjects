package com.mandeum.dessert39.Main.My39.Point

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentPointBinding


class PointFragment : Fragment() {

    private var _binding: FragmentPointBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPointBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val pointModel: ArrayList<PointModel> = ArrayList()
        val rvAdapter : PointAdapter = PointAdapter(requireContext(), pointModel)
        val rv : RecyclerView = binding.pointRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        pointModel.add(PointModel("200","이벤트","","2021.10.10","2021.10.10 ~ 2021.11.30"))
        pointModel.add(PointModel("200","이벤트","","2021.10.10","2021.10.10 ~ 2021.11.30"))
        pointModel.add(PointModel("","","400","2021.10.10",""))
        pointModel.add(PointModel("200","이벤트","","2021.10.10","2021.10.10 ~ 2021.11.30"))
        pointModel.add(PointModel("","","400","2021.10.10",""))
        pointModel.add(PointModel("","","400","2021.10.10",""))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}