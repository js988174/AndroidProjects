package com.mandeum.dessert39.Main.Card

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentCardBinding
import com.mandeum.dessert39.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.card_custom_dialog.*
import kotlinx.android.synthetic.main.fragment_card.*


class CardFragment : Fragment() {

    private var _binding : FragmentCardBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardBinding.inflate(layoutInflater)
        val findUserCard = ""

//           if (findUserCard == "no") {
//                // 등록된 카드가 없음
//                card_layout_none.isGone = true
//                card_register_layout.isGone = true
//
//            }
//           else if (findUserCard == "normal") {
//                // 일반 카드 등록
//                card_layout_none.isGone = true
//                card_register_layout.isGone = true
//                auto_charging_settings.isGone = true
//
//            }
//            else if (findUserCard == "auto") {
//                // 자동 충전 등록
//                card_layout_none.isGone = true
//                card_register_layout.isGone = true
//                charging_settings.isGone = true
//
//            }


        binding.cardLayout.setOnClickListener {
            var direction = CardFragmentDirections.actionCardFragmentToCardChoiceFragment()
            it.findNavController().navigate(direction)
        }

        // 카드 자동 충전 설정
        binding.autoChargingSettings.setOnClickListener {
            var direction = CardFragmentDirections.actionCardFragmentToCardChargeFragment("","","normal", "")
            findNavController().navigate(direction)
        }

        // 카드 일반 충전
        binding.chargingSettings.setOnClickListener {
            var direction = CardFragmentDirections.actionCardFragmentToCardChargeFragment("","","auto", "")
            findNavController().navigate(direction)
        }

        // 디자인 변경
        binding.cardChangeBtn.setOnClickListener {
            var direction = CardFragmentDirections.actionCardFragmentToCardChoiceFragment("")
        }


        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_cardFragment_to_homeFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_cardFragment_to_alarmFragment)
        }
        binding.navMy39.setOnClickListener {
            it.findNavController().navigate(R.id.action_cardFragment_to_profileFragment)
        }
        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_cardFragment_to_orderFragment)
        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}