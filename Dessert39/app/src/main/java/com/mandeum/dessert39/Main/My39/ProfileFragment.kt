package com.mandeum.dessert39.Main.My39

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mandeum.dessert39.Main.My39.History.ReviewFragment
import com.mandeum.dessert39.Main.Order.dialog.OrderCustomFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAlarmBinding
import com.mandeum.dessert39.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    lateinit var alertDialog : android.app.AlertDialog
    lateinit var builder : android.app.AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater)


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

        binding.textView58.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_rewardFragment)
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
            it.findNavController().navigate(R.id.action_profileFragment_to_orderHistoryFragment)
        }

        binding.layout5.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_cardChargeFragment2)
        }

        binding.layout6.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_receiptFragment)
        }

        binding.layout7.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_boardFragment)
        }

        binding.layout8.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_questionFragment)
        }

        binding.layout9.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_customerQuestionFragment)
        }

        binding.layout10.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_my39ShopFragment)
        }

        binding.layout11.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_eventFragment)
        }

        binding.layout12.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_policyFragment)
        }

        binding.layout13.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_settingFragment)
        }

        binding.layout14.setOnClickListener {
            logout()
        }

        return binding.root
    }

    private fun logout() {
        try{
            var str_tittle = "????????????"
            var str_message = "?????? ???????????? ???????????????????"


            builder = android.app.AlertDialog.Builder(requireContext()
            )
            builder.setTitle(str_tittle) //????????? ????????? ??????
            builder.setMessage(str_message) //????????? ?????? ??????
            builder.setPositiveButton("??????", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            })

            builder.setNegativeButton("??????", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            })
            // builder.setCancelable(false) //?????? ???????????? ???????????? ???????????? ?????????????????? ??????
            alertDialog = builder.create()
            try {
                alertDialog.show()
            }
            catch (e : Exception){
                e.printStackTrace()
            }
        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}