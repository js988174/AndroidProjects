package com.rud.mandeumtalk.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.databinding.FragmentGuideBinding
import com.rud.mandeumtalk.databinding.FragmentHomeBinding


class GuideFragment : Fragment() {

	private lateinit var binding : FragmentGuideBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_guide, container, false)

		binding.homeTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_homeFragment)
		}

		binding.portTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_portfolioFragment)
		}

		binding.guideTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_self)
		}

		binding.boardTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_boardFragment)
		}

		binding.accountTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_accountFragment)
		}

		return binding.root
	}


}