package com.mandeum.dessert39.Main.Alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.findNavController
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAlarmBinding
import com.mandeum.dessert39.databinding.FragmentSettingBinding
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    private var _binding : FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSettingBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.eventSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //체크된 상태 취소 시 코드
            } else {
                //체크된 상태로 만들 시 코드
            }
        }


        binding.reviewSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //체크된 상태 취소 시 코드
            } else {
                //체크된 상태로 만들 시 코드
            }
        }

        binding.orderSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //체크된 상태 취소 시 코드
            } else {
                //체크된 상태로 만들 시 코드
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}