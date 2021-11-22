package com.mandeum.dessert39.Main.Card

import android.app.Dialog
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.Main.Card.animation.collapse
import com.mandeum.dessert39.Main.Card.animation.expand
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentCardChargeBinding
import kotlinx.android.synthetic.main.card_custom_dialog.*
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.android.synthetic.main.fragment_card_charge.*
import kotlinx.android.synthetic.main.fragment_card_choice.*
import java.io.File
import java.io.IOException
import java.util.*


class CardChargeFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentCardChargeBinding
    private lateinit var card: CardListModel
    private lateinit var findUserCard : String

    var autoChargeSelected: Boolean = false
    var normalChargeSelected : Boolean = false
    var standard5000 : Boolean = false
    var standard10000 : Boolean = false
    var standard30000 : Boolean = false
    var standard50000 : Boolean = false
    var standard70000 : Boolean = false
    var standard_direct : Boolean = false
    var auto5000 : Boolean = false
    var auto10000 : Boolean = false
    var auto30000 : Boolean = false
    var auto50000 : Boolean = false
    var auto70000 : Boolean = false
    var standard_direct2 : Boolean = false
    var standardM : String = ""
    var autoM : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCardChargeBinding.inflate(layoutInflater)

        val cardImage : ImageView = binding.memberCard
        val args: CardChargeFragmentArgs by navArgs()

        val cardImageUrl = args.imageUrl
        val cameraImage = args.cameraImage
        val albumImage = args.albumImage.toUri()


        if (cardImageUrl != "" && albumImage.toString() == "" && cameraImage == "") {
            Glide.with(requireContext()).load(cardImageUrl).into(cardImage)
        } else if (cardImageUrl == "" && albumImage.toString() != "" && cameraImage == "") {
            cardImage.setImageURI(albumImage)
        } else if (cardImageUrl == "" && albumImage.toString() == "" && cameraImage != "") {
        val file  = File(cameraImage)

        if (Build.VERSION.SDK_INT >= 28) {

            val source : ImageDecoder.Source = ImageDecoder.createSource(requireContext().contentResolver, Uri.fromFile(file))

            try {
                val image = ImageDecoder.decodeBitmap(source)

                if (image != null) {

                    cardImage.setImageBitmap(image)
                }
            } catch (e : IOException) {
                e.printStackTrace()
            }
        } else if (Build.VERSION.SDK_INT < 28) {
            try {
                val image = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,
                    Uri.fromFile(file))

                if (image != null) {

                    cardImage.setImageBitmap(image)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
          }
        }

        binding.up2.setOnClickListener(this)
        binding.upText.setOnClickListener(this)


//        when (findUserCard) {
//            "no" -> {
//                // 등록된 카드가 없음
//                cardName.isGone = false
//                card_registered_layout.isGone = true
//                card_charge_layout.isGone = false
//                card_normal_layout.isGone = true
//                card_auto_layout.isGone = true
//
//            }
//            "normal" -> {
//                // 일반 카드 등록
//                cardName.isGone = true
//                card_registered_layout.isGone = false
//                card_charge_layout.isGone = true
//                card_normal_layout.isGone = false
//                card_auto_layout.isGone = true
//
//            }
//            "auto" -> {
//                // 자동 충전 등록
//                cardName.isGone = true
//                card_registered_layout.isGone = false
//                card_charge_layout.isGone = true
//                card_normal_layout.isGone = true
//                card_auto_layout.isGone = false
//            }
//        }


        binding.cardCharge.setOnClickListener {
            val direction = CardChargeFragmentDirections.actionCardChargeFragmentToCardFragment()
        }

        binding.chargeOff.setOnClickListener {

        }

        binding.changeSettings.setOnClickListener {

        }

        binding.findImage.setOnClickListener {
            it.findNavController().navigate(R.id.action_cardChargeFragment_to_cardChoiceFragment)
        }


        // button 조건식

        binding.autoCharge.setOnClickListener {
                    if (!normalChargeSelected) {
                        autoChargeSelected = isSelected(autoChargeSelected, autoCharge)
                    } else if (normalChargeSelected) {
                        normalChargeSelected = isSelected(normalChargeSelected, normal_charge)
                        autoChargeSelected = isSelected(autoChargeSelected, autoCharge)
                    }
            if (autoChargeSelected) {
                standard_money_layout.isGone = false
            } else if (!autoChargeSelected) {
                standard_money_layout.isGone = true
            }

            when {
                standard5000 -> {
                    standard5000 = isSelected(standard5000, standard_5000)
                }
                standard10000 -> {
                    standard10000 = isSelected(standard10000, standard_10000)
                }
                standard30000 -> {
                    standard30000 = isSelected(standard30000, standard_30000)
                }
                standard50000 -> {
                    standard50000 = isSelected(standard50000, standard_50000)
                }
                standard70000 -> {
                    standard70000 = isSelected(standard70000, standard_70000)
                }
                standard_direct -> {
                    standard_direct = isSelected(standard_direct, direct_input)
                }
            }

            when {
                auto5000 -> {
                    auto5000 = isSelected(auto5000, auto_charge_5000)
                }
                auto10000 -> {
                    auto10000 = isSelected(auto10000, auto_charge_10000)
                }
                auto30000 -> {
                    auto30000 = isSelected(auto30000, auto_charge_30000)
                }
                auto50000 -> {
                    auto50000 = isSelected(auto50000, auto_charge_50000)
                }
                auto70000 -> {
                    auto70000 = isSelected(auto70000, auto_charge_70000)
                }
                standard_direct2 -> {
                    standard_direct2 = isSelected(standard_direct2, direct_input3)
                }
            }



        }

        binding.normalCharge.setOnClickListener {

            standard_money_layout.isGone = true

            if (!autoChargeSelected) {
                normalChargeSelected = isSelected(normalChargeSelected, normal_charge)
            } else if (autoChargeSelected) {
                normalChargeSelected = isSelected(normalChargeSelected, normal_charge)
                autoChargeSelected = isSelected(autoChargeSelected, autoCharge)
            }


            when {
                standard5000 -> {
                    standard5000 = isSelected(standard5000, standard_5000)
                }
                standard10000 -> {
                    standard10000 = isSelected(standard10000, standard_10000)
                }
                standard30000 -> {
                    standard30000 = isSelected(standard30000, standard_30000)
                }
                standard50000 -> {
                    standard50000 = isSelected(standard50000, standard_50000)
                }
                standard70000 -> {
                    standard70000 = isSelected(standard70000, standard_70000)
                }
                standard_direct -> {
                    standard_direct = isSelected(standard_direct, direct_input)
                }
            }

            when {
                auto5000 -> {
                    auto5000 = isSelected(auto5000, auto_charge_5000)
                }
                auto10000 -> {
                    auto10000 = isSelected(auto10000, auto_charge_10000)
                }
                auto30000 -> {
                    auto30000 = isSelected(auto30000, auto_charge_30000)
                }
                auto50000 -> {
                    auto50000 = isSelected(auto50000, auto_charge_50000)
                }
                auto70000 -> {
                    auto70000 = isSelected(auto70000, auto_charge_70000)
                }
                standard_direct2 -> {
                    standard_direct2 = isSelected(standard_direct2, direct_input3)
                }

            }
        }

            binding.standard5000.setOnClickListener {
                if(autoChargeSelected) {
                    when {
                        standard5000 -> {

                        }
                        standard10000 -> {
                            standard10000 = isSelected(standard10000, standard_10000)
                        }
                        standard30000 -> {
                            standard30000 = isSelected(standard30000, standard_30000)
                        }
                        standard50000 -> {
                            standard50000 = isSelected(standard50000, standard_50000)
                        }
                        standard70000 -> {
                            standard70000 = isSelected(standard70000, standard_70000)
                        }
                        standard_direct -> {
                            standard_direct = isSelected(standard_direct, direct_input)
                            direct_input_layout.isGone = true
                        }
                    }
                    standard5000 = isSelected(standard5000, standard_5000)
                    standardM = standardMoney(standard5000, standard10000, standard30000,
                        standard50000, standard70000)
                    Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                        standardM, autoM)
                }


            }

            binding.standard10000.setOnClickListener {
                if (autoChargeSelected) {
                    when {
                        standard5000 -> {
                            standard5000 = isSelected(standard5000, standard_5000)
                        }
                        standard10000 -> {

                        }
                        standard30000 -> {
                            standard30000 = isSelected(standard30000, standard_30000)
                        }
                        standard50000 -> {
                            standard50000 = isSelected(standard50000, standard_50000)
                        }
                        standard70000 -> {
                            standard70000 = isSelected(standard70000, standard_70000)
                        }
                        standard_direct -> {
                            standard_direct = isSelected(standard_direct, direct_input)
                            direct_input_layout.isGone = true
                        }
                    }
                    standard10000 = isSelected(standard10000, standard_10000)
                    standardM = standardMoney(standard5000, standard10000, standard30000,
                        standard50000, standard70000)
                    Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                        standardM, autoM)
                }
            }

            binding.standard30000.setOnClickListener {
                if (autoChargeSelected) {
                    when {
                        standard5000 -> {
                            standard5000 = isSelected(standard5000, standard_5000)
                        }
                        standard10000 -> {
                            standard10000 = isSelected(standard10000, standard_10000)
                        }
                        standard30000 -> {

                        }
                        standard50000 -> {
                            standard50000 = isSelected(standard50000, standard_50000)
                        }
                        standard70000 -> {
                            standard70000 = isSelected(standard70000, standard_70000)
                        }
                        standard_direct -> {
                            standard_direct = isSelected(standard_direct, direct_input)
                            direct_input_layout.isGone = true
                        }
                    }
                    standard30000 = isSelected(standard30000, standard_30000)
                    standardM = standardMoney(standard5000, standard10000, standard30000,
                        standard50000, standard70000)
                    Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                        standardM, autoM)
                }
            }

            binding.standard50000.setOnClickListener {
                if (autoChargeSelected) {
                    when {
                        standard5000 -> {
                            standard5000 = isSelected(standard5000, standard_5000)
                        }
                        standard10000 -> {
                            standard10000 = isSelected(standard10000, standard_10000)
                        }
                        standard30000 -> {
                            standard30000 = isSelected(standard30000, standard_30000)
                        }
                        standard50000 -> {

                        }
                        standard70000 -> {
                            standard70000 = isSelected(standard70000, standard_70000)
                        }
                        standard_direct -> {
                            standard_direct = isSelected(standard_direct, direct_input)
                            direct_input_layout.isGone = true
                        }
                    }
                    standard50000 = isSelected(standard50000, standard_50000)
                    standardM = standardMoney(standard5000, standard10000, standard30000,
                        standard50000, standard70000)
                    Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                        standardM, autoM)
                }
            }

            binding.standard70000.setOnClickListener {
                if (autoChargeSelected) {
                    when {
                        standard5000 -> {
                            standard5000 = isSelected(standard5000, standard_5000)
                        }
                        standard10000 -> {
                            standard10000 = isSelected(standard10000, standard_10000)
                        }
                        standard30000 -> {
                            standard30000 = isSelected(standard30000, standard_30000)
                        }
                        standard50000 -> {
                            standard50000 = isSelected(standard50000, standard_50000)
                        }
                        standard70000 -> {

                        }
                        standard_direct -> {
                            standard_direct = isSelected(standard_direct, direct_input)
                            direct_input_layout.isGone = true
                        }
                    }
                    standard70000 = isSelected(standard70000, standard_70000)
                    standardM = standardMoney(standard5000, standard10000, standard30000,
                        standard50000, standard70000)
                    Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                        standardM, autoM)
                }
            }

            binding.directInput.setOnClickListener {
                if (autoChargeSelected) {
                    when {
                        standard5000 -> {
                            standard5000 = isSelected(standard5000, standard_5000)
                        }
                        standard10000 -> {
                            standard10000 = isSelected(standard10000, standard_10000)
                        }
                        standard30000 -> {
                            standard30000 = isSelected(standard30000, standard_30000)
                        }
                        standard50000 -> {
                            standard50000 = isSelected(standard50000, standard_50000)
                        }
                        standard70000 -> {
                            standard70000 = isSelected(standard70000, standard_70000)
                        }
                        standard_direct -> {

                        }

                    }


                    standard_direct = isSelected(standard_direct, direct_input)
                    if (standard_direct) {
                        direct_input_layout.isGone = false
                    } else if (!standard_direct) {
                        direct_input_layout.isGone = true
                    }
                }
            }


        binding.chargeSelfArea.addTextChangedListener(object :TextWatcher{
            private var inputText = ""

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                var text = p0.toString() ?: ""
                if (!TextUtils.isEmpty(text) && !TextUtils.equals(inputText, text)) {
                    var strNumber: String

                    if (text.contains(".")) {
                        strNumber = text.substring(0, text.indexOf("."))
                    } else {
                        strNumber = text
                    }

                    strNumber = strNumber.replace(",", "")
                    var doubleText = strNumber.toDoubleOrNull() ?: 0.0
                    val dec = DecimalFormat("###,###")

                    inputText = dec.format(doubleText)
                    charge_self_area.setText(inputText)
                    charge_self_area.setSelection(inputText.length)

                    standardM = p0.toString()
                }
            }
        })

        binding.autoCharge5000.setOnClickListener {
            if (autoChargeSelected || normalChargeSelected) {

                when {
                    auto5000 -> {

                    }
                    auto10000 -> {
                        auto10000 = isSelected(auto10000, auto_charge_10000)
                    }
                    auto30000 -> {
                        auto30000 = isSelected(auto30000, auto_charge_30000)
                    }
                    auto50000 -> {
                        auto50000 = isSelected(auto50000, auto_charge_50000)
                    }
                    auto70000 -> {
                        auto70000 = isSelected(auto70000, auto_charge_70000)
                    }
                    standard_direct2 -> {
                        standard_direct2 = isSelected(standard_direct2, direct_input3)
                        direct_input3_layout.isGone = true
                    }
                }
                auto5000 = isSelected(auto5000, auto_charge_5000)
                autoM = autoMoney(auto5000, auto10000, auto30000, auto50000,auto70000)
                if (autoChargeSelected) {
                    Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                        standardM, autoM)
                } else if(normalChargeSelected) {
                    Activation2(autoChargeSelected, normalChargeSelected, card_register, card_charge,autoM)
                }
            }
        }

        binding.autoCharge10000.setOnClickListener {
            if (autoChargeSelected || normalChargeSelected) {

                when {
                    auto5000 -> {
                        auto5000 = isSelected(auto5000, auto_charge_5000)
                    }
                    auto10000 -> {

                    }
                    auto30000 -> {
                        auto30000 = isSelected(auto30000, auto_charge_30000)
                    }
                    auto50000 -> {
                        auto50000 = isSelected(auto50000, auto_charge_50000)
                    }
                    auto70000 -> {
                        auto70000 = isSelected(auto70000, auto_charge_70000)
                    }
                    standard_direct2 -> {
                        standard_direct2 = isSelected(standard_direct2, direct_input3)
                        direct_input3_layout.isGone = true
                    }
                }
                auto10000 = isSelected(auto10000, auto_charge_10000)
                autoM = autoMoney(auto5000, auto10000, auto30000, auto50000,auto70000)
            }
            if (autoChargeSelected) {
                Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                    standardM, autoM)
            } else if(normalChargeSelected) {
                Activation2(autoChargeSelected, normalChargeSelected, card_register, card_charge,autoM)
            }
        }

        binding.autoCharge30000.setOnClickListener {
            if (autoChargeSelected || normalChargeSelected) {

                when {
                    auto5000 -> {
                        auto5000 = isSelected(auto5000, auto_charge_5000)
                    }
                    auto10000 -> {
                        auto10000 = isSelected(auto10000, auto_charge_10000)
                    }
                    auto30000 -> {

                    }
                    auto50000 -> {
                        auto50000 = isSelected(auto50000, auto_charge_50000)
                    }
                    auto70000 -> {
                        auto70000 = isSelected(auto70000, auto_charge_70000)
                    }
                    standard_direct2 -> {
                        standard_direct2 = isSelected(standard_direct2, direct_input3)
                        direct_input3_layout.isGone = true
                    }
                }
                auto30000 = isSelected(auto30000, auto_charge_30000)
                autoM = autoMoney(auto5000, auto10000, auto30000, auto50000,auto70000)
            }
            if (autoChargeSelected) {
                Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                    standardM, autoM)
            } else if(normalChargeSelected) {
                Activation2(autoChargeSelected, normalChargeSelected, card_register, card_charge,autoM)
            }
        }

        binding.autoCharge50000.setOnClickListener {
            if (autoChargeSelected || normalChargeSelected) {

                when {
                    auto5000 -> {
                        auto5000 = isSelected(auto5000, auto_charge_5000)
                    }
                    auto10000 -> {
                        auto10000 = isSelected(auto10000, auto_charge_10000)
                    }
                    auto30000 -> {
                        auto30000 = isSelected(auto30000, auto_charge_30000)
                    }
                    auto50000 -> {

                    }
                    auto70000 -> {
                        auto70000 = isSelected(auto70000, auto_charge_70000)
                    }
                    standard_direct2 -> {
                        standard_direct2 = isSelected(standard_direct2, direct_input3)
                        direct_input3_layout.isGone = true
                    }
                }
                auto50000 = isSelected(auto50000, auto_charge_50000)
                autoM = autoMoney(auto5000, auto10000, auto30000, auto50000,auto70000)
            }
            if (autoChargeSelected) {
                Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                    standardM, autoM)
            } else if(normalChargeSelected) {
                Activation2(autoChargeSelected, normalChargeSelected, card_register, card_charge,autoM)
            }
        }

        binding.autoCharge70000.setOnClickListener {
            if (autoChargeSelected || normalChargeSelected) {

                when {
                    auto5000 -> {
                        auto5000 = isSelected(auto5000, auto_charge_5000)
                    }
                    auto10000 -> {
                        auto10000 = isSelected(auto10000, auto_charge_10000)
                    }
                    auto30000 -> {
                        auto30000 = isSelected(auto30000, auto_charge_30000)
                    }
                    auto50000 -> {
                        auto50000 = isSelected(auto50000, auto_charge_50000)
                    }
                    auto70000 -> {

                    }
                    standard_direct2 -> {
                        standard_direct2 = isSelected(standard_direct2, direct_input3)
                        direct_input3_layout.isGone = true
                    }
                }
                auto70000 = isSelected(auto70000, auto_charge_70000)
                autoM = autoMoney(auto5000, auto10000, auto30000, auto50000,auto70000)
            }
            if (autoChargeSelected) {
                Activation(autoChargeSelected, normalChargeSelected, card_register, card_charge,
                    standardM, autoM)
            } else if(normalChargeSelected) {
                Activation2(autoChargeSelected, normalChargeSelected, card_register, card_charge,autoM)
            }
        }

        binding.directInput3.setOnClickListener {
            if (autoChargeSelected || normalChargeSelected) {

                when {
                    auto5000 -> {
                        auto5000 = isSelected(auto5000, auto_charge_5000)
                    }
                    auto10000 -> {
                        auto10000 = isSelected(auto10000, auto_charge_10000)
                    }
                    auto30000 -> {
                        auto30000 = isSelected(auto30000, auto_charge_30000)
                    }
                    auto50000 -> {
                        auto50000 = isSelected(auto50000, auto_charge_50000)
                    }
                    auto70000 -> {
                        auto70000 = isSelected(auto70000, auto_charge_70000)
                    }
                    standard_direct2 -> {

                    }
                }
                standard_direct2 = isSelected(standard_direct2, direct_input3)
                if (standard_direct2) {
                    direct_input3_layout.isGone = false
                } else if (!standard_direct2) {
                    direct_input3_layout.isGone = true
                }
            }
        }


        binding.chargeSelfArea3.addTextChangedListener(object :TextWatcher{
            private var inputText = ""

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                var text = p0.toString() ?: ""
                if (!TextUtils.isEmpty(text) && !TextUtils.equals(inputText, text)) {
                    var strNumber: String

                    if (text.contains(".")) {
                        strNumber = text.substring(0, text.indexOf("."))
                    } else {
                        strNumber = text
                    }

                    strNumber = strNumber.replace(",", "")
                    var doubleText = strNumber.toDoubleOrNull() ?: 0.0
                    val dec = DecimalFormat("###,###")

                    inputText = dec.format(doubleText)
                    charge_self_area3.setText(inputText)
                    charge_self_area3.setSelection(inputText.length)

                    autoM = p0.toString()
                }
            }
        })


        binding.cardRegister.setOnClickListener {

            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.card_custom_dialog)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            dialog.ok.setOnClickListener {
                val direction = CardChargeFragmentDirections.actionCardChargeFragmentToCardFragment()
            }

            dialog.no.setOnClickListener {
                dialog.dismiss()
            }

            dialog.base_balance.text = standardM
            dialog.auto_charge_balance.text = autoM
            dialog.show()
        }




        return binding.root
    }


    private fun isSelected(selected: Boolean, button: Button) : Boolean {

        var select : Boolean = selected

        if (!selected) {
            button.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
            button.setBackgroundResource(R.drawable.background_radius_black_button2)
            select = true

        } else if (selected) {
            button.setTextColor(ContextCompat.getColor(context!!, R.color.black2))
            button.setBackgroundResource(R.drawable.background_radius_white_button2)

            select = false
        }

        return select
    }


    private fun standardMoney(standard5000: Boolean, standard10000: Boolean, standard30000: Boolean,
    standard50000: Boolean, standard70000: Boolean) : String {

        var standardM = ""

        if (standard5000) {
            standardM = "5,000"
        } else if (standard10000) {
            standardM = "10,000"
        } else if (standard30000) {
            standardM = "30,000"
        } else if (standard50000) {
            standardM = "50,000"
        } else if (standard70000) {
            standardM = "70,000"
        }
        return standardM
    }

    private fun autoMoney(auto5000: Boolean, auto10000: Boolean, auto30000: Boolean,
                              auto50000: Boolean, auto70000: Boolean) : String {

        var autoM = ""

        if (auto5000) {
            autoM = "5,000"
        } else if (auto10000) {
            autoM = "10,000"
        } else if (auto30000) {
            autoM = "30,000"
        } else if (auto50000) {
            autoM = "50,000"
        } else if (auto70000) {
            autoM = "70,000"
        }
        return  autoM
    }

    private fun Activation(autoChargeSelected: Boolean, normalChargeSelected:Boolean, card_register :Button, card_charge: Button,
                           standardM : String, autoM : String) {
            if (!autoChargeSelected && !normalChargeSelected) {
                card_register.setBackgroundResource(R.drawable.background_radius_default)
                card_charge.setBackgroundResource(R.drawable.background_radius_default)
            } else if (standardM == "") {
                card_register.setBackgroundResource(R.drawable.background_radius_default)
                card_charge.setBackgroundResource(R.drawable.background_radius_default)
            } else if (autoM == "") {
                card_register.setBackgroundResource(R.drawable.background_radius_default)
                card_charge.setBackgroundResource(R.drawable.background_radius_default)
            } else {
                card_register.setBackgroundResource(R.drawable.background_radius_maincolor)
                card_charge.setBackgroundResource(R.drawable.background_radius_maincolor)
            }
    }


    private fun Activation2(autoChargeSelected: Boolean, normalChargeSelected:Boolean, card_register :Button, card_charge: Button, autoM : String) {
        if (!autoChargeSelected && !normalChargeSelected) {
            card_register.setBackgroundResource(R.drawable.background_radius_default)
            card_charge.setBackgroundResource(R.drawable.background_radius_default)
        } else if (autoM == "") {
            card_register.setBackgroundResource(R.drawable.background_radius_default)
            card_charge.setBackgroundResource(R.drawable.background_radius_default)
        } else {
            card_register.setBackgroundResource(R.drawable.background_radius_maincolor)
            card_charge.setBackgroundResource(R.drawable.background_radius_maincolor)
        }
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                R.id.upText, R.id.up2 -> {
                    if (detail.visibility == View.GONE) {
                        detail.expand(scrollView = nested_scroll_view)
                        upText.text = "펼치기"
                        up2.setImageResource(R.drawable.down)
                    } else { //VISIBLE
                        detail.collapse()
                        upText.text = "접기"
                        up2.setImageResource(R.drawable.up)
                    }
                }
            }
        }
    }
}