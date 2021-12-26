package com.mandeum.dessert39.Main.Order

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mandeum.dessert39.Main.Order.dialog.OrderFindShopFragment
import com.mandeum.dessert39.Main.Order.slide.*
import com.mandeum.dessert39.Main.Order.sub.Adapter.ViewPager
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderBinding
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

import android.content.Context.CLIPBOARD_SERVICE
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController



class OrderFragment : Fragment(R.layout.fragment_order) {

        private  var _binding: FragmentOrderBinding? = null
        private val binding get() = _binding!!

        private lateinit var callback: OnBackPressedCallback



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrderBinding.inflate(layoutInflater)



        binding.orderIcon.setOnClickListener {
            val action = OrderFragmentDirections.actionOrderFragmentToOrderCartFragment()
            findNavController().navigate(action)
        }

        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_orderFragment_to_homeFragment)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_orderFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_orderFragment_to_alarmFragment)
        }
        binding.navMy39.setOnClickListener {
            it.findNavController().navigate(R.id.action_orderFragment_to_profileFragment)
        }

        binding.findImage.setOnClickListener {
            it.findNavController().navigate(R.id.action_orderFragment_to_searchFragment)
        }

        binding.copyBtn.setOnClickListener {
            val clipboard: ClipboardManager = requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", "Text to copy")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "주소를 복사했습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.changeBtn.setOnClickListener {

        }


        val findShop =  OrderFindShopFragment()
        findShop.isCancelable = false
        activity?.supportFragmentManager?.let { findShop.show(it, findShop.tag) }

        val viewPager2: ViewPager2 = binding.viewPager

        val adapter = ViewPager(this)

        viewPager2.adapter = adapter
        viewPager2.isUserInputEnabled = false


        fun Change(change: Int) {

            val typeface = Typeface.createFromAsset(requireContext().assets, "pretendard_bold.otf")
            val typeface2 =
                Typeface.createFromAsset(requireContext().assets, "pretendardregular.otf")

            when (change) {
                0 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_LEFT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.recommandText1.typeface = typeface
                    binding.recommandView.isVisible = true

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                1 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_LEFT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.dessertText.typeface = typeface
                    binding.dessertView.isVisible = true

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                2 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_LEFT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.coffeeText.typeface = typeface
                    binding.coffeeView.isVisible = true

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                3 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_LEFT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.nonCoffeeText.typeface = typeface
                    binding.nonCoffeeView.isVisible = true

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                4 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_LEFT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.milkTeaText.typeface = typeface
                    binding.milkTeaView.isVisible = true

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                5 -> {
//                    binding.scrollView.fullScroll(ScrollView.FOCUS_LEFT)
                    binding.scrollView.scrollX = 500
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.blendingText.typeface = typeface
                    binding.blendingView.isVisible = true

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                6 -> {
//                    binding.scrollView.fullScroll(ScrollView.FOCUS_RIGHT)
                    binding.scrollView.scrollX = 400
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.aidText.typeface = typeface
                    binding.aidView.isVisible = true

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                7 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_RIGHT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.frappeText.typeface = typeface
                    binding.frappeView.isVisible = true

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                8 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_RIGHT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.fromageText.typeface = typeface
                    binding.fromageView.isVisible = true

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                9 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_RIGHT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.iceText.typeface = typeface
                    binding.iceView.isVisible = true

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.favoriteText1.typeface = typeface2
                    binding.favoriteView.isVisible = false
                }
                10 -> {
                    binding.scrollView.fullScroll(ScrollView.FOCUS_RIGHT)
                    binding.recommandText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.recommandText1.typeface = typeface2
                    binding.recommandView.isVisible = false

                    binding.dessertText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.dessertText.typeface = typeface2
                    binding.dessertView.isVisible = false

                    binding.coffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.coffeeText.typeface = typeface2
                    binding.coffeeView.isVisible = false

                    binding.nonCoffeeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.nonCoffeeText.typeface = typeface2
                    binding.nonCoffeeView.isVisible = false

                    binding.milkTeaText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.milkTeaText.typeface = typeface2
                    binding.milkTeaView.isVisible = false

                    binding.blendingText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.blendingText.typeface = typeface2
                    binding.blendingView.isVisible = false

                    binding.aidText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.aidText.typeface = typeface2
                    binding.aidView.isVisible = false

                    binding.frappeText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.frappeText.typeface = typeface2
                    binding.frappeView.isVisible = false

                    binding.fromageText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.fromageText.typeface = typeface2
                    binding.fromageView.isVisible = false

                    binding.iceText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.colorGray8))
                    binding.iceText.typeface = typeface2
                    binding.iceView.isVisible = false

                    binding.favoriteText1.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.black2))
                    binding.favoriteText1.typeface = typeface
                    binding.favoriteView.isVisible = true
                }
            }
            viewPager2.currentItem = change
        }

        binding.recommandBtn.setOnClickListener {
            Change(0)
        }

        binding.dessertBtn.setOnClickListener {
            Change(1)
        }

        binding.coffeeBtn.setOnClickListener {
            Change(2)
        }

        binding.nonCoffeeBtn.setOnClickListener {
            Change(3)
        }

        binding.milkTeaBtn.setOnClickListener {
            Change(4)
        }

        binding.blendingBtn.setOnClickListener {
            Change(5)
        }

        binding.aidBtn.setOnClickListener {
            Change(6)
        }

        binding.frappeBtn.setOnClickListener {
            Change(7)
        }

        binding.fromageBtn.setOnClickListener {
            Change(8)
        }

        binding.iceBtn.setOnClickListener {
            Change(9)
        }

        binding.favoriteBtn.setOnClickListener {
            Change(10)
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
                    4 -> {
                        Change(4)
                    }
                    5 -> {
                        Change(5)
                    }
                    6 -> {
                        Change(6)
                    }
                    7 -> {
                        Change(7)
                    }
                    8 -> {
                        Change(8)
                    }
                    9 -> {
                        Change(9)
                    }
                    10 -> {
                        Change(10)
                    }
                }
            }
        })

        return binding.root
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action: NavDirections = OrderFragmentDirections.actionOrderFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }


    override fun onDestroyView() {

        val viewPager2 = _binding?.viewPager

        viewPager2?.let {
            it.adapter = null
        }

        super.onDestroyView()
        _binding = null

    }
}