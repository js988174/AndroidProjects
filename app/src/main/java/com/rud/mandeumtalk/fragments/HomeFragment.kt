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
import com.rud.mandeumtalk.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

	private lateinit var binding : FragmentHomeBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

		binding.homeIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_self)
		}
		binding.homeText.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_self)
		}

		binding.portfolioIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_portfolioFragment)
		}
		binding.portfolioText.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_portfolioFragment)
		}

		binding.boardIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_guideFragment)
		}
		binding.boardText.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_guideFragment)
		}

		binding.contactUsIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_boardFragment)
		}
		binding.contactUsText.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_boardFragment)
		}

		binding.accountIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
		}
		binding.accountText.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
		}
		return binding.root
	}
}