package com.mandeum.dessert39.Main.Order.sub.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mandeum.dessert39.Login.ServerApi.Model.Order.CategoryModel
import com.mandeum.dessert39.Login.ServerApi.Model.Order.DessertListModel
import com.mandeum.dessert39.Login.ServerApi.Model.Order.GoodsModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.Card.CardChargeFragmentArgs
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.OrderFragment
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.Main.Order.dialog.OrderCustomFragment
import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuAdapter
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderMenuDetailBinding
import kotlinx.android.synthetic.main.fragment_order_menu_detail.*
import kotlin.concurrent.thread


class OrderMenuDetailFragment : Fragment() {

    companion object {
        private var _binding: FragmentOrderMenuDetailBinding? = null
        private val binding get() = _binding!!
        private lateinit var callback: OnBackPressedCallback

    }
    lateinit var thread : HomeActivity

    var hotSelected : Boolean = false
    var iceSelected : Boolean = false
    var tall : Boolean = false
    var grande : Boolean = false
    var vanti : Boolean = false
    var shopCup : Boolean = false
    var tumbler : Boolean = false
    var disCup : Boolean = false
    var favoriteSelect : Boolean = false
//    private var dessert_price:TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrderMenuDetailBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val args: OrderMenuDetailFragmentArgs by navArgs()

        val no: Int = args.no
        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        Toast.makeText(requireContext(), "no =$no", Toast.LENGTH_SHORT).show()

        binding.cartBtn.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.CustomAlertDialog)
            bottomSheetDialog.setContentView(R.layout.dialog_menu_plus)
            bottomSheetDialog.setCanceledOnTouchOutside(false)
            bottomSheetDialog.setCancelable(false)
            bottomSheetDialog.findViewById<ImageView>(R.id.close_btn)?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.findViewById<Button>(R.id.order_go)?.setOnClickListener {
                val action = OrderMenuDetailFragmentDirections.actionOrderMenuDetailFragmentToOrderCartFragment()
                findNavController().navigate(action)
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.findViewById<Button>(R.id.order_ok)?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.show()
        }

//        binding.cartBtn.setOnClickListener {
//            requireActivity().getSharedPreferences("shopping_cart", Context.MODE_PRIVATE).edit().apply {
//                putString("cart_title", args.title)
//                putString("cart_content", args.content)
//                putString("cart_image", args.imageView)
//                putString("cart_amount", args.price)
//            }.apply()
//        }

        thread(start = true) {
            val detailModel: GoodsModel = ServerApi.menudetail(token.toString(),no)
            if (detailModel.connection) {
                if (detailModel.errCode == "0000") {
                    thread.runOnUiThread {
                        Toast.makeText(
                            requireContext(), "성공",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "${detailModel.connection}오류",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }




        binding.favorite1.setOnCheckedChangeListener { checkBox, isChecked ->

                if (isChecked) {

                } else {

                }
//            if (favoriteSelect) {
//                binding.favorite1.setImageResource(R.drawable.star)
//                favoriteSelect = true
//            } else if (!favoriteSelect) {
//                binding.favorite1.setImageResource(R.drawable.star_gray)
//                favoriteSelect = false
//            }
        }

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()

        }

        binding.infoBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_orderMenuDetailFragment_to_orderMenuInfoFragment)
        }

        val count : TextView = binding.basketNumber

        if (count.equals(1)) {
                binding.basketMinus1.setImageResource(R.drawable.minus_gray)
            } else {
                binding.basketMinus1.setImageResource(R.drawable.minus_black)
            }

        binding.basketPlus.setOnClickListener {
           val countNumber : Int = count.text.toString().toInt()
            count.text = "${countNumber + 1}"

        }

        binding.basketMinus.setOnClickListener {
            val countNumber : Int = count.text.toString().toInt()
          if ( countNumber > 1) {
              count.text = "${countNumber - 1}"
          }
        }

        binding.hot.setOnClickListener {
            if (!iceSelected) {
                hotSelected = isSelectedRed(hotSelected, hot)
            } else if (iceSelected) {
                iceSelected = isSelectedBlue(iceSelected, ice)
                hotSelected = isSelectedRed(hotSelected, hot)
            }

            when {
            tall -> {
                tall = isSelectedBlack(tall, tall_btn)
            }
                grande -> {
                    grande = isSelectedBlack(grande, grande_btn)
                }
                vanti -> {
                    vanti = isSelectedBlack(vanti, venti_btn)
                }

            }

            when {
                shopCup -> {
                    shopCup = isSelectedBlack(shopCup, shop_cup_btn)
                }
                tumbler -> {
                    tumbler = isSelectedBlack(tumbler, tumbler_btn)
                }
                disCup -> {
                    disCup = isSelectedBlack(disCup, cup_btn)
                }
            }
        }

        binding.ice.setOnClickListener {
            if (!hotSelected) {
                iceSelected = isSelectedBlue(iceSelected, ice)
            } else if (hotSelected) {
                iceSelected = isSelectedBlue(iceSelected, ice)
                hotSelected = isSelectedRed(hotSelected, hot)
            }

            when {
                tall -> {
                    tall = isSelectedBlack(tall, tall_btn)
                }
                grande -> {
                    grande = isSelectedBlack(grande, grande_btn)
                }
                vanti -> {
                    vanti = isSelectedBlack(vanti, venti_btn)
                }
            }

            when {
                shopCup -> {
                    shopCup = isSelectedBlack(shopCup, shop_cup_btn)
                }
                tumbler -> {
                    tumbler = isSelectedBlack(tumbler, tumbler_btn)
                }
                disCup -> {
                    disCup = isSelectedBlack(disCup, cup_btn)
                }
            }
        }

        binding.tallBtn.setOnClickListener {
            if (iceSelected || hotSelected) {

                when {
                    tall -> {

                    }
                    grande -> {
                        grande = isSelectedBlack(grande, grande_btn)
                    }
                    vanti -> {
                        vanti = isSelectedBlack(vanti, venti_btn)
                    }
                }

                tall = isSelectedBlack(tall, tall_btn)
            }
        }

        binding.grandeBtn.setOnClickListener {
            if (iceSelected || hotSelected) {

                when {
                    tall -> {
                        tall = isSelectedBlack(tall, tall_btn)
                    }
                    grande -> {

                    }
                    vanti -> {
                        vanti = isSelectedBlack(vanti, venti_btn)
                    }
                }


                      grande = isSelectedBlack(grande, grande_btn)
            }
        }

        binding.ventiBtn.setOnClickListener {
            if (iceSelected || hotSelected) {

                when {
                    tall -> {
                        tall = isSelectedBlack(tall, tall_btn)
                    }
                    grande -> {
                        grande = isSelectedBlack(grande, grande_btn)
                    }
                    vanti -> {

                    }
                }

                vanti = isSelectedBlack(vanti, venti_btn)
            }
        }

        binding.shopCupBtn.setOnClickListener {
            if (iceSelected || hotSelected) {


                when {
                    shopCup -> {

                    }
                    tumbler -> {
                        tumbler = isSelectedBlack(tumbler, tumbler_btn)
                    }
                    disCup -> {
                        disCup = isSelectedBlack(disCup, cup_btn)
                    }
                }
                shopCup = isSelectedBlack(shopCup, shop_cup_btn)
            }
        }

        binding.tumblerBtn.setOnClickListener {
            if (iceSelected || hotSelected) {


                when {
                    shopCup -> {
                        shopCup = isSelectedBlack(shopCup, shop_cup_btn)
                    }
                    tumbler -> {

                    }
                    disCup -> {
                        disCup = isSelectedBlack(disCup, cup_btn)
                    }
                }
                tumbler = isSelectedBlack(tumbler, tumbler_btn)
            }
        }

        binding.cupBtn.setOnClickListener {
            if (iceSelected || hotSelected) {

                when {
                    shopCup -> {
                        shopCup = isSelectedBlack(shopCup, shop_cup_btn)
                    }
                    tumbler -> {
                        tumbler = isSelectedBlack(tumbler, tumbler_btn)
                    }
                    disCup -> {

                    }
                }
                disCup = isSelectedBlack(disCup, cup_btn)
            }
        }



        binding.customBtn.setOnClickListener {
            val customOption =  OrderCustomFragment()
            customOption.isCancelable = false
            activity?.supportFragmentManager?.let { customOption.show(it, customOption.tag) }
        }

        return binding.root
    }


    private fun isSelectedBlack(selected: Boolean, button: Button): Boolean {

        var select : Boolean = selected

        if (!select) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
            button.setBackgroundResource(R.drawable.background_radius_dessert_black)

            select = true
        } else if (select) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray2))
            button.setBackgroundResource(R.drawable.background_radius_dessert_gray)

            select = false
        }

        return select
    }


    private fun isSelectedRed(selected: Boolean, button: Button): Boolean {

        var select : Boolean = selected

        if (!select) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            button.setBackgroundResource(R.drawable.background_radius_dessert_red)

            select = true
        } else if (select) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray2))
            button.setBackgroundResource(R.drawable.background_radius_dessert_gray)

            select = false
        }

        return select
    }


    private fun isSelectedBlue(selected: Boolean, button: Button): Boolean {

        var select : Boolean = selected

        if (!select) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
            button.setBackgroundResource(R.drawable.background_radius_dessert_blue)

            select = true
        } else if (select) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray2))
            button.setBackgroundResource(R.drawable.background_radius_dessert_gray)

            select = false
        }

        return  select
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}