package com.mandeum.dessert39.Main.My39.Shop

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.mandeum.dessert39.Main.Order.selectShop.select.SelectViewPager
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentMy39ShopBinding


class My39ShopFragment : Fragment() {

    private var _binding: FragmentMy39ShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMy39ShopBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val viewPager2 : ViewPager2 = binding.viewPager

        val adapter = My39ShopViewPager(this)

        viewPager2.adapter = adapter
        viewPager2.isUserInputEnabled = false

        fun Change(change : Int) {

            val typeface = Typeface.createFromAsset(requireContext().assets, "pretendard_bold.otf")
            val typeface2 = Typeface.createFromAsset(requireContext().assets, "pretendardregular.otf")


            when (change) {
                0 -> {
                    binding.nearShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.nearShopText.typeface = typeface
                    binding.nearShopView.isVisible = true

                    binding.allShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.allShopText.typeface = typeface2
                    binding.allShopView.isVisible = false

                    binding.frequentShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.frequentShopText.typeface = typeface2
                    binding.frequentShopView.isVisible = false
                }
                1 -> {
                    binding.nearShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.nearShopText.typeface = typeface2
                    binding.nearShopView.isVisible = false

                    binding.allShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.allShopText.typeface = typeface
                    binding.allShopView.isVisible = true

                    binding.frequentShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.frequentShopText.typeface = typeface2
                    binding.frequentShopView.isVisible = false
                }
                2 -> {
                    binding.nearShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.nearShopText.typeface = typeface2
                    binding.nearShopView.isVisible = false

                    binding.allShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.allShopText.typeface = typeface2
                    binding.allShopView.isVisible = false

                    binding.frequentShopText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.frequentShopText.typeface = typeface
                    binding.frequentShopView.isVisible = true
                }
            }
            viewPager2.currentItem = change
        }

        binding.nearShopBtn.setOnClickListener {
            Change(0)
        }

        binding.allShopBtn.setOnClickListener {
            Change(1)
        }

        binding.frequentShopBtn.setOnClickListener {
            Change(2)
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        Change(0)
                    }
                    1 -> {
                        Change(1)
                    }
                    2 -> {
                        Change(2)
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