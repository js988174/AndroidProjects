package com.mandeum.dessert39.Main.My39.policy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentPolicyBinding


class PolicyFragment : Fragment() {

    lateinit var binding : FragmentPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPolicyBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            it.findNavController().navigate(R.id.action_policyFragment_to_profileFragment)
        }

        return binding.root
    }


}