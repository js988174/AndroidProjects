package com.mandeum.dessert39.Main.Order.sub.detail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mandeum.dessert39.Login.ServerApi.Model.Bookmark.MenuBookMarkModel
import com.mandeum.dessert39.Login.ServerApi.Model.Login.SnsLoginModel
import com.mandeum.dessert39.Login.ServerApi.Model.Order.GoodsModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderDetailBinding
import kotlinx.android.synthetic.main.fragment_card_charge.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_order_menu_detail.*
import kotlinx.android.synthetic.main.my39_story_item.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.concurrent.thread
import kotlin.math.roundToInt


class OrderDetailFragment : Fragment() {

    private var _binding: FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailBinding.inflate(layoutInflater)

        thread = context as HomeActivity

        var hotSelected : Boolean = false
        var iceSelected : Boolean = false
        var tall : Boolean = false
        var grande : Boolean = false
        var vanti : Boolean = false
        var shopCup : Boolean = false
        var tumbler : Boolean = false
        var disCup : Boolean = false
        var favoriteSelect : Boolean = false
        val args: OrderDetailFragmentArgs by navArgs()
        val no: Int = args.no
        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = shared.edit()
        val token = shared.getString("LoginToken", "")
        val basketPrice = binding.basketPrice



        binding.cartBtn.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.CustomAlertDialog)
            bottomSheetDialog.setContentView(R.layout.dialog_menu_plus)
            bottomSheetDialog.setCanceledOnTouchOutside(false)
            bottomSheetDialog.setCancelable(false)
            bottomSheetDialog.findViewById<ImageView>(R.id.close_btn)?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.findViewById<Button>(R.id.order_go)?.setOnClickListener {
                val action = OrderDetailFragmentDirections.actionOrderDetailFragmentToOrderCartFragment()
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
        val count : TextView = binding.basketNumber
        thread(start = true) {

            val detailModel: GoodsModel = ServerApi.menudetail(token.toString(),no)
            val menuDetail = GoodsModel(detailModel.connection, detailModel.errCode,detailModel.no,
                detailModel.name, detailModel.eng_name, detailModel.imgHot,detailModel.imgIce, detailModel.price,
                detailModel.cate1, detailModel.cate2, detailModel.priceDessert,
                detailModel.useOpt,detailModel.optSyrup,detailModel.optShot,detailModel.optDecaffeine,
                detailModel.optHard, detailModel.optPearl, detailModel.optNatadcoco, detailModel.priceHotG
            ,   detailModel.priceIceG,detailModel.priceHotV, detailModel.priceIceV, detailModel.priceHotL,
                detailModel.priceIceL, detailModel.optSpecial)

                Log.d("menuDetail", menuDetail.toString())
            if (detailModel.connection) {
                if (detailModel.errCode == "0000") {
                    thread.runOnUiThread {
                        Glide.with(requireContext()).load(menuDetail.imgHot).into(binding.dessertImage)
                        binding.kName.text = detailModel.name
                        binding.eName.text = detailModel.eng_name
                        binding.price.text = detailModel.price
                        binding.basketPrice.text = detailModel.price

                        if (detailModel.cate1 == 1) {
                            binding.sizeText.isGone = true
                            binding.hot.isGone = true
                            binding.ice.isGone = true
                            binding.view.isGone = true
                            binding.grandeBtn.isGone = true
                            binding.ventiBtn.isGone = true
                            binding.tallBtn.isGone = true
                            binding.view2.isGone = true
                            binding.view3.isGone = true
                            binding.cupText.isGone = true
                            binding.shopCupBtn.isGone = true
                            binding.tumblerBtn.isGone = true
                            binding.cupBtn.isGone = true
                            binding.customBtn.isGone = true
                            binding.sale.isGone = true
                        }

                        if (detailModel.imgHot.isNullOrEmpty()) {
                            binding.hot.visibility = View.INVISIBLE
                        } else if (detailModel.imgIce.isNullOrEmpty()) {
                            binding.ice.visibility = View.INVISIBLE
                        }

                        binding.hot.setOnClickListener {

                            Glide.with(requireContext()).load(menuDetail.imgHot).into(binding.dessertImage)
                            if (!iceSelected) {
                                hotSelected = isSelectedRed(hotSelected, hot)
                                hotSelected = true
                            } else if (iceSelected) {
                                iceSelected = isSelectedBlue(iceSelected, ice)
                                hotSelected = isSelectedRed(hotSelected, hot)
                                hotSelected = false
                            }

                            when {
                                hotSelected -> {
                                    hotSelected = isSelectedRed(hotSelected, hot)
                                }
                                iceSelected -> {
                                    iceSelected = isSelectedBlue(iceSelected, ice)
                                }
                            }
                            hotSelected = isSelectedRed(hotSelected, hot)
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
                            Glide.with(requireContext()).load(menuDetail.imgIce).into(binding.dessertImage)
                            if (!hotSelected) {
                                iceSelected = isSelectedBlue(iceSelected, ice)
                                iceSelected = true
                            } else if (hotSelected) {
                                iceSelected = isSelectedBlue(iceSelected, ice)
                                hotSelected = isSelectedRed(hotSelected, hot)
                                iceSelected = false
                            }

                            when {
                                hotSelected -> {
                                    hotSelected = isSelectedRed(hotSelected, hot)
                                }
                                iceSelected -> {
                                    iceSelected = isSelectedBlue(iceSelected, ice)
                                }
                            }
                            iceSelected = isSelectedBlue(iceSelected, ice)
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

                        binding.basketPlus.setOnClickListener {
                            val countNumber : Int = count.text.toString().toInt()
                            count.text = "${countNumber + 1}"
                            val double = basketPrice.toString().toInt() + basketPrice.toString().toInt()
                            basketPrice.text = double.toString()
                        }

                        binding.basketMinus.setOnClickListener {
                            val countNumber : Int = count.text.toString().toInt()
                            if ( countNumber > 1) {
                                count.text = "${countNumber - 1}"
                            }
                        }



                        binding.tallBtn.setOnClickListener {
                            val total: Int = basket_price?.text.toString().toInt()
                            if (iceSelected || hotSelected) {
                                if (!tall) {
                                    btnEvent1(binding.tallBtn)
                                    btnEvent2(binding.grandeBtn)
                                    btnEvent2(binding.ventiBtn)
                                    binding.tallBtn.isClickable = false
                                    binding.grandeBtn.isClickable = true
                                    binding.ventiBtn.isClickable = true
                                    tall = true
                                    grande = false
                                    vanti = false
                                }
                            }
                        }

                        binding.grandeBtn.setOnClickListener {
                            val total: Int = basketPrice.text.toString().toInt()
                            if (iceSelected || hotSelected) {
                                if (!grande) {
                                    btnEvent1(binding.grandeBtn)
                                    btnEvent2(binding.tallBtn)
                                    btnEvent2(binding.ventiBtn)
                                    binding.tallBtn.isClickable = true
                                    binding.grandeBtn.isClickable = false
                                    binding.ventiBtn.isClickable = true
                                    tall = false
                                    grande = true
                                    vanti = false
                                }
                            }
                            val grandePrice = detailModel.priceHotV.toInt() - total
                            basketPrice.text = "${total + grandePrice}"
                        }

                        binding.ventiBtn.setOnClickListener {
                            val total: Int = basket_price?.text.toString().toInt()
                            if (iceSelected || hotSelected) {
                                if (!vanti) {
                                    btnEvent1(binding.ventiBtn)
                                    btnEvent2(binding.tallBtn)
                                    btnEvent2(binding.grandeBtn)
                                    binding.tallBtn.isClickable = true
                                    binding.grandeBtn.isClickable = true
                                    binding.ventiBtn.isClickable = false
                                    tall = false
                                    grande = false
                                    vanti = true
                                }
                            }
                            val largePrice = detailModel.priceHotL.toInt() - total
                            basketPrice.text = "${total + largePrice}"
                        }

                        binding.shopCupBtn.setOnClickListener {
                            val total: Int = basketPrice.text.toString().toInt()
                            if (iceSelected || hotSelected) {
                                if (!shopCup) {
                                    btnEvent1(binding.shopCupBtn)
                                    btnEvent2(binding.tumblerBtn)
                                    btnEvent2(binding.cupBtn)
                                    binding.shopCupBtn.isClickable = false
                                    binding.tumblerBtn.isClickable = true
                                    binding.cupBtn.isClickable = true
                                    shopCup = true
                                    tumbler = false
                                    disCup = false
                                }
                            }

                        }



                        binding.tumblerBtn.setOnClickListener {
                            val tumblerDis : Int = basket_price?.text.toString().toInt()
                            if (iceSelected || hotSelected) {
                                if (!tumbler) {
                                    btnEvent1(binding.tumblerBtn)
                                    btnEvent2(binding.shopCupBtn)
                                    btnEvent2(binding.cupBtn)
                                    binding.shopCupBtn.isClickable = true
                                    binding.tumblerBtn.isClickable = false
                                    binding.cupBtn.isClickable = true
                                    shopCup = false
                                    tumbler = true
                                    disCup = false
                                }

                            }
                            val resultDiscount = tumblerDis * 0.05
                            val totalPrice = tumblerDis.toString().toInt() - resultDiscount.toDouble().roundToInt()
                            basketPrice.text = totalPrice.toString()
                        }

                        binding.cupBtn.setOnClickListener {
                            if (iceSelected || hotSelected) {
                                if (!disCup) {
                                    btnEvent1(binding.cupBtn)
                                    btnEvent2(binding.shopCupBtn)
                                    btnEvent2(binding.tumblerBtn)
                                    binding.shopCupBtn.isClickable = true
                                    binding.tumblerBtn.isClickable = true
                                    binding.cupBtn.isClickable = false
                                    shopCup = false
                                    tumbler = false
                                    disCup = true
                                }
                            }
                        }

                        binding.customBtn.setOnClickListener {

                            val dialog = BottomSheetDialog(requireContext(), R.style.CustomAlertDialog)
                            dialog.setContentView(R.layout.order_custom)
                            dialog.behavior.state = BottomSheetBehavior.STATE_SETTLING

                            var special : Boolean = false
                            var special2 : Boolean = false
                            var dCaffein : Boolean = false
                            var dCaffein2 : Boolean = false
                            var thick : Boolean = false
                            var thick2 : Boolean = false
                            var pul : Boolean = false
                            var pul2 : Boolean = false
                            var nacota : Boolean = true
                            var nacota2 : Boolean = false


                            val dChangeNoBtn: AppCompatButton? = dialog.findViewById(R.id.d_no_change)
                            val specialBtn1: AppCompatButton? = dialog.findViewById(R.id.special_no_change)
                            val specialBtn2: AppCompatButton? = dialog.findViewById(R.id.special_change)
                            val dChangeBtn: AppCompatButton? = dialog.findViewById(R.id.d_change)
                            val normal: AppCompatButton? = dialog.findViewById(R.id.thick_normal)
                            val much: AppCompatButton? = dialog.findViewById(R.id.thick_much)
                            val pulNormal: AppCompatButton? = dialog.findViewById(R.id.pul_normal)
                            val pulMuch: AppCompatButton? = dialog.findViewById(R.id.pul_much)
                            val cocoNormal: AppCompatButton? = dialog.findViewById(R.id.coco_normal)
                            val cocoMuch: AppCompatButton? = dialog.findViewById(R.id.coco_much)
                            val shotMinus: ConstraintLayout? = dialog.findViewById(R.id.minus_btn1)
                            val shotPlus: ConstraintLayout? = dialog.findViewById(R.id.plus_btn1)
                            val vanilaMinus: ConstraintLayout? = dialog.findViewById(R.id.vanila_minus)
                            val vanilaPlus: ConstraintLayout? = dialog.findViewById(R.id.vanila_plus)
                            val hazelnutMinus: ConstraintLayout? = dialog.findViewById(R.id.hazelnut_minus)
                            val hazelnutPlus: ConstraintLayout? = dialog.findViewById(R.id.hazelnut_plus)
                            val caramelMinus: ConstraintLayout? = dialog.findViewById(R.id.caramel_minus)
                            val caramelPlus: ConstraintLayout? = dialog.findViewById(R.id.caramel_plus)
                            val shotCount: TextView? = dialog.findViewById(R.id.number1)
                            val vanilaCount: TextView? = dialog.findViewById(R.id.vanila_number)
                            val hazelnutCount: TextView? = dialog.findViewById(R.id.hazelnut_number)
                            val caramelCount: TextView? = dialog.findViewById(R.id.caramel_number)
                            val shotPrice : TextView? = dialog.findViewById(R.id.count_price)
                            val vanilaPrice : TextView? = dialog.findViewById(R.id.vanila_price)
                            val hazelnutPrice : TextView? = dialog.findViewById(R.id.coco_price)
                            val caramelPrice : TextView? = dialog.findViewById(R.id.caramel_price)
                            val totalPrice : TextView? = dialog.findViewById(R.id.result)

                            dialog.findViewById<ImageView>(R.id.close)?.setOnClickListener {
                                dialog.dismiss()
                            }



                            specialBtn1?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()
                                if (!special) {
                                    btnEvent1(specialBtn1)
                                    specialBtn2?.let { it1 -> btnEvent2(it1) }
                                    specialBtn1.isClickable = false
                                    specialBtn2?.isClickable = true
                                    special = true
                                    special2 = false
                                    detailModel.optSpecial = "0"
                                }

                                if (!special2) {
                                    totalPrice?.text = "${total - 1000}"
                                }

                            }

                            specialBtn2?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()
                                if (!special2) {
                                    btnEvent1(specialBtn2)
                                    specialBtn1?.let { it1 -> btnEvent2(it1) }
                                    specialBtn2.isClickable = false
                                    specialBtn1?.isClickable = true
                                    special = false
                                    special2 = true
                                    detailModel.optSpecial = "1"
                                }

                                totalPrice?.text = "${total + 1000}"
                            }

                            dChangeNoBtn?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()
                                if (!dCaffein) {
                                    btnEvent1(dChangeNoBtn)
                                    dChangeBtn?.let { it1 -> btnEvent2(it1) }
                                    dChangeNoBtn.isClickable = false
                                    dChangeBtn?.isClickable = true
                                    dCaffein = true
                                    dCaffein2 = false
                                }
                                detailModel.optDecaffeine = "0"
                                totalPrice?.text = "${total - 300}"

                            }

                            dChangeBtn?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()
                                if (!dCaffein2) {
                                    btnEvent1(dChangeBtn)
                                    dChangeNoBtn?.let { it1 -> btnEvent2(it1) }
                                    dChangeBtn.isClickable = false
                                    dChangeNoBtn?.isClickable = true
                                    dCaffein = false
                                    dCaffein2 = true
                                    detailModel.optDecaffeine = "1"
                                }

                                totalPrice?.text = "${total + 300}"
                            }

                            normal?.setOnClickListener {
                                if (!thick) {
                                    btnEvent1(normal)
                                    much?.let { it1 -> btnEvent2(it1) }
                                    normal.isClickable = false
                                    much?.isClickable = true
                                    thick = true
                                    thick2 = false
                                    detailModel.optHard = "0"
                                }


                            }

                            much?.setOnClickListener {
                                if (!thick2) {
                                    btnEvent1(much)
                                    normal?.let { it1 -> btnEvent2(it1) }
                                    normal?.isClickable = true
                                    much.isClickable = false
                                    thick = false
                                    thick2 = true
                                    detailModel.optHard = "1"
                                }

                            }

                            pulNormal?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()

                                if (!pul) {
                                    btnEvent1(pulNormal)
                                    pulMuch?.let { it1 -> btnEvent2(it1) }
                                    pulNormal.isClickable = false
                                    pulMuch?.isClickable = true
                                    pul = true
                                    pul2 = false
                                }
                                detailModel.optPearl = "0"
                                totalPrice?.text = "${total - 500}"
                            }

                            pulMuch?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()

                                if (!pul2) {
                                    btnEvent1(pulMuch)
                                    pulNormal?.let { it1 -> btnEvent2(it1) }
                                    pulNormal?.isClickable = true
                                    pulMuch.isClickable = false
                                    pul = false
                                    pul2 = true
                                }
                                detailModel.optPearl = "1"
                                totalPrice?.text = "${total + 500}"
                            }


                            cocoNormal?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()

                                if (!nacota) {
                                    btnEvent1(cocoNormal)
                                    cocoMuch?.let { it1 -> btnEvent2(it1) }
                                    cocoNormal.isClickable = false
                                    cocoMuch?.isClickable = true
                                    nacota = true
                                    nacota2 = false
                                }
                                detailModel.optNatadcoco = "0"
                                totalPrice?.text = "${total - 800}"
                            }

                            cocoMuch?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()

                                if (!nacota2) {
                                    btnEvent1(cocoMuch)
                                    cocoNormal?.let { it1 -> btnEvent2(it1) }
                                    cocoMuch.isClickable = false
                                    cocoNormal?.isClickable = true
                                    nacota = false
                                    nacota2 = true
                                }
                                detailModel.optNatadcoco = "1"
                                totalPrice?.text = "${total + 800}"

                            }


                            shotMinus?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()
                                val number1: Int = shotCount?.text.toString().toInt()
                                val shot: Int = shotPrice?.text.toString().toInt()
                                if (number1 > 0) {
                                    shotCount?.text = "${number1 - 1}"
                                    totalPrice?.text = "${total - 500}"
                                    shotPrice?.text = "${shot - 500}"
                                }
                            }

                            shotPlus?.setOnClickListener {
                                val total: Int = totalPrice?.text.toString().toInt()
                                val number1: Int = shotCount?.text.toString().toInt()
                                val shot: Int = shotPrice?.text.toString().toInt()
                                shotCount?.text = "${number1 + 1}"
                                totalPrice?.text = "${total + 500}"
                                shotPrice?.text = "${shot + 500}"
                                detailModel.optShot = "1"
                            }


                            vanilaMinus?.setOnClickListener {
                                val number2: Int = vanilaCount?.text.toString().toInt()
                                val total: Int = totalPrice?.text.toString().toInt()
                                val vanila: Int = vanilaPrice?.text.toString().toInt()
                                if (number2 > 0) {
                                    vanilaCount?.text = "${number2 - 1}"
                                    totalPrice?.text = "${total - 500}"
                                    vanilaPrice?.text = "${vanila - 500}"
                                }
                            }

                            vanilaPlus?.setOnClickListener {
                                val number2: Int = vanilaCount?.text.toString().toInt()
                                val total: Int = totalPrice?.text.toString().toInt()
                                val vanila: Int = vanilaPrice?.text.toString().toInt()
                                vanilaCount?.text = "${number2 + 1}"
                                totalPrice?.text = "${total + 500}"
                                vanilaPrice?.text = "${vanila + 500}"
                            }

                            hazelnutMinus?.setOnClickListener {
                                val number3 : Int = hazelnutCount?.text.toString().toInt()
                                val total: Int = totalPrice?.text.toString().toInt()
                                val hazelnut: Int = hazelnutPrice?.text.toString().toInt()
                                if (number3 > 0) {
                                    hazelnutCount?.text = "${number3 - 1}"
                                    totalPrice?.text = "${total - 500}"
                                    hazelnutPrice?.text = "${hazelnut - 500}"
                                }
                            }

                            hazelnutPlus?.setOnClickListener {
                                val number3 : Int = hazelnutCount?.text.toString().toInt()
                                val total: Int = totalPrice?.text.toString().toInt()
                                val hazelnut: Int = hazelnutPrice?.text.toString().toInt()
                                hazelnutCount?.text = "${number3 + 1}"
                                totalPrice?.text = "${total + 500}"
                                hazelnutPrice?.text = "${hazelnut + 500}"
                            }

                            caramelMinus?.setOnClickListener {
                                val number4 : Int = caramelCount?.text.toString().toInt()
                                val total: Int = totalPrice?.text.toString().toInt()
                                val caramel: Int = caramelPrice?.text.toString().toInt()
                                if (number4 > 0) {
                                    caramelCount?.text = "${number4 -1}"
                                    totalPrice?.text = "${total - 500}"
                                    caramelPrice?.text = "${caramel - 500}"
                                }
                            }

                            caramelPlus?.setOnClickListener {
                                val number4 : Int = caramelCount?.text.toString().toInt()
                                val total: Int = totalPrice?.text.toString().toInt()
                                val caramel: Int = caramelPrice?.text.toString().toInt()
                                caramelCount?.text = "${number4 + 1}"
                                totalPrice?.text = "${total + 500}"
                                caramelPrice?.text = "${caramel + 500}"
                            }

                            dialog?.findViewById<ConstraintLayout>(R.id.check_btn)?.setOnClickListener {
                                val a = basket_price.text
                                val b = totalPrice?.text
                                val c = a.toString().toInt() + b.toString().toInt()
                                basket_price.text = c.toString()
                                dialog.dismiss()
                            }

                            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            dialog.setCancelable(false)
                            dialog.show()
                        }


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


            val jsonObject = JSONObject()
            jsonObject.put("no",no)
            jsonObject.put("name", detailModel.name)
            jsonObject.put("eng_name", detailModel.eng_name)
            jsonObject.put("img_hot", detailModel.imgHot)
            jsonObject.put("img_ice", detailModel.imgIce)
            jsonObject.put("opt_syrup", detailModel.optSyrup)
            jsonObject.put("opt_shot", detailModel.optShot)
            jsonObject.put("opt_decaffein", detailModel.optDecaffeine)
            jsonObject.put("opt_pearl", detailModel.optHard)
            jsonObject.put("opt_pearl", detailModel.optPearl)
            jsonObject.put("opt_natadcoco", detailModel.optNatadcoco)
            jsonObject.put("opt_special", detailModel.optSpecial)
            jsonObject.put("total", basketPrice)
            val body = JsonParser.parseString(jsonObject.toString()) as JsonObject
            Log.d("body", body.toString())
        }

        val checkPoint = shared.getBoolean("favorite", false)

        binding.favorite1.setOnCheckedChangeListener { checkBox, isChecked ->
            if (!favoriteSelect) {
                val A : String ?= null
                bookMark(token.toString(),no, A.toString())
                editor.putBoolean("favorite", true)
                Toast.makeText(requireContext(),"A",Toast.LENGTH_SHORT).show()
            } else if (favoriteSelect) {
                val D : String ?= null
                bookMark(token.toString(),no, D.toString())
                editor.putBoolean("favorite", false)
                Toast.makeText(requireContext(),"D",Toast.LENGTH_SHORT).show()
            }

            if (!checkPoint) {
                favoriteSelect = true
            } else if (checkPoint) {
                favoriteSelect = false
            }

        }

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()

        }

        binding.infoBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_orderDetailFragment_to_orderMenuInfoFragment)
        }



        if (count.equals(1)) {
            binding.basketMinus1.setImageResource(R.drawable.minus_gray)
        } else {
            binding.basketMinus1.setImageResource(R.drawable.minus_black)
        }





        return binding.root
    }

    private fun btnEvent1 (button: Button) {
        button.setBackgroundResource(R.drawable.background_radius_dessert_black)
        button.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black2
            )
        )
    }

    private fun btnEvent2 (button: Button) {
        button.setBackgroundResource(R.drawable.background_radius_dessert_gray)
        button.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorGray2
            )
        )
    }

    @SuppressLint("HardwareIds")
    private fun bookMark(token:String, no: Int, part:String) {

        thread(start = true) {
            val user: MenuBookMarkModel = ServerApi.funMenuMark(token,no, part)
            val bookMarkModel = MenuBookMarkModel(user.connection, user.errCode)
            if (bookMarkModel.errCode == "0000") {

                thread.runOnUiThread {
                    Toast.makeText(requireContext(), "정보 저장", Toast.LENGTH_SHORT).show()

                }
            }

            }

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