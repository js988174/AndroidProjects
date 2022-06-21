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
import com.mandeum.dessert39.Main.My39.Shop.viewPager.ShopViewPager
import com.mandeum.dessert39.Main.Order.selectShop.select.SelectViewPager
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentMy39ShopDetailBinding


class My39ShopDetailFragment : Fragment() {

    private var _binding: FragmentMy39ShopDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMy39ShopDetailBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }


        val viewPager2 : ViewPager2 = binding.shopViewPager

        val adapter = ShopViewPager(this)

        viewPager2.adapter = adapter
        viewPager2.isUserInputEnabled = false

        fun Change(change : Int) {

            val typeface = Typeface.createFromAsset(requireContext().assets, "pretendard_bold.otf")
            val typeface2 = Typeface.createFromAsset(requireContext().assets, "pretendardregular.otf")


            when (change) {
                0 -> {
                    binding.informationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.informationText.typeface = typeface
                    binding.informationView.isVisible = true

                    binding.storyText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.storyText.typeface = typeface2
                    binding.storyView.isVisible = false

                    binding.eventText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.eventText.typeface = typeface2
                    binding.eventView.isVisible = false

                    binding.starText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.starText.typeface = typeface2
                    binding.starView.isVisible = false
                }
                1 -> {
                    binding.informationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.informationText.typeface = typeface2
                    binding.informationView.isVisible = false

                    binding.storyText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.storyText.typeface = typeface
                    binding.storyView.isVisible = true

                    binding.eventText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.eventText.typeface = typeface2
                    binding.eventView.isVisible = false

                    binding.starText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.starText.typeface = typeface2
                    binding.starView.isVisible = false
                }
                2 -> {
                    binding.informationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.informationText.typeface = typeface2
                    binding.informationView.isVisible = false

                    binding.storyText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.storyText.typeface = typeface2
                    binding.storyView.isVisible = false

                    binding.eventText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.eventText.typeface = typeface
                    binding.eventView.isVisible = true

                    binding.starText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.starText.typeface = typeface2
                    binding.starView.isVisible = false
                }
                3 -> {
                    binding.informationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.informationText.typeface = typeface2
                    binding.informationView.isVisible = false

                    binding.storyText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.storyText.typeface = typeface2
                    binding.storyView.isVisible = false

                    binding.eventText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.eventText.typeface = typeface2
                    binding.eventView.isVisible = false

                    binding.starText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                    binding.starText.typeface = typeface
                    binding.starView.isVisible = true
                }
            }
            viewPager2.currentItem = change
        }

        binding.informationBtn.setOnClickListener {
            Change(0)
        }

        binding.storyBtn.setOnClickListener {
            Change(1)
        }

        binding.eventBtn.setOnClickListener {
            Change(2)
        }

        binding.starBtn.setOnClickListener {
            Change(3)
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
                    3 -> {
                        Change(3)
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