package com.mandeum.dessert39.Main.My39

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAlarmBinding
import com.mandeum.dessert39.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)


        binding.navMy39.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_self)
        }
        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_alarmFragment)
        }
        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_orderFragment)
        }

        binding.layout1.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_myInfoFragment)
        }

        binding.layout2.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_myCouponFragment2)
        }

        binding.layout3.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_pointFragment)
        }

        binding.layout4.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_policyFragment)
        }

        binding.layout12.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_policyFragment)
        }




        return binding.root
    }


}