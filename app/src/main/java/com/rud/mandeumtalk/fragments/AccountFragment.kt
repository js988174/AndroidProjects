package com.rud.mandeumtalk.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

	private lateinit var binding : FragmentAccountBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

		binding.homeIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_guideFragment)
		}
		binding.homeText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_guideFragment)
		}

		binding.portfolioIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_portfolioFragment)
		}
		binding.portfolioText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_portfolioFragment)
		}

		binding.boardIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
		}
		binding.boardText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
		}

		binding.contactUsIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_boardFragment)
		}
		binding.contactUsText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_boardFragment)
		}

		binding.accountIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_self)
		}
		binding.accountText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_self)
		}

		return binding.root
	}
}