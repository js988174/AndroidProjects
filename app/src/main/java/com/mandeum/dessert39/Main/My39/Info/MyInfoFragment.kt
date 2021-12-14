package com.mandeum.dessert39.Main.My39.Info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.findNavController
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentMyInfoBinding


class MyInfoFragment : Fragment() {

    private var _binding : FragmentMyInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMyInfoBinding.inflate(layoutInflater)

        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}