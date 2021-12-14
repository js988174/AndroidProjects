package com.mandeum.dessert39.Main.My39.Sound

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mandeum.dessert39.Main.Order.OrderFragment
import com.mandeum.dessert39.Main.Order.sub.Adapter.ViewPager
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentCustomerQuestionBinding


class CustomerQuestionFragment : Fragment() {

    private var _binding: FragmentCustomerQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomerQuestionBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_customerQuestionFragment_to_orderFragment)
        }

        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_customerQuestionFragment_to_homeFragment)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_customerQuestionFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_customerQuestionFragment_to_alarmFragment)
        }

        val viewPager2: ViewPager2 = binding.viewPager

        val adapter = ViewPager2(this)

        viewPager2.adapter = adapter
        viewPager2.isUserInputEnabled = false


        fun change(change : Int) {

            val typeface = Typeface.createFromAsset(requireContext().assets, "pretendard_bold.otf")
            val typeface2 =
                Typeface.createFromAsset(requireContext().assets, "pretendardregular.otf")

            when(change) {
                0 -> {
                    binding.recommandText1.setTextColor(
                        ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.recommandText1.typeface = typeface
                    binding.recommandView.isVisible = true

                    binding.dessertText.setTextColor(
                        ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false
                }

                1 -> {
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.dessertText.typeface = typeface
                    binding.dessertView.isVisible = true
                }
            }
            viewPager2.currentItem = change
        }

        binding.recommandBtn.setOnClickListener {
            change(0)
        }

        binding.dessertBtn.setOnClickListener {
            change(1)
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        change(0)
                    }
                    1 -> {
                        change(1)
                    }

                }
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}