package com.mandeum.dessert39.Main.My39.Info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentMyInfoBinding


class MyInfoFragment : Fragment() {

    lateinit var binding : FragmentMyInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyInfoBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            it.findNavController().navigate(R.id.action_myInfoFragment_to_profileFragment)
        }

        binding.settingSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //체크된 상태 취소 시 코드
            } else {
                //체크된 상태로 만들 시 코드
            }
        }

        return binding.root
    }


}