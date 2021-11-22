package com.mandeum.dessert39.Main.My39.Coupon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentCouponBinding
import com.mandeum.dessert39.databinding.FragmentMyCouponBinding


class MyCouponFragment : Fragment() {

    lateinit var binding: FragmentMyCouponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyCouponBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            it.findNavController().navigate(R.id.action_myCouponFragment2_to_profileFragment)
        }

        return binding.root
    }


}