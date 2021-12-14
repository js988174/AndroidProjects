package com.mandeum.dessert39.Main.My39.History

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.icu.util.LocaleData
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentTermSettingBinding
import kotlinx.android.synthetic.main.fragment_term_setting.*
import kotlinx.android.synthetic.main.point_item_layout.*
import java.text.DateFormat
import java.text.SimpleDateFormat

import java.util.*
import android.content.Intent
import android.widget.DatePicker
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_order_history.*


class TermSettingFragment : DialogFragment() {

    private var picker: Calendar? = null
    private var picker2: Calendar? = null

    companion object {

        private var _binding: FragmentTermSettingBinding? = null
        private val binding get() = _binding!!

        var monthSelect: Boolean = false
        var month3Select: Boolean = false
        var yearSelect: Boolean = false
        var directSelect: Boolean = false


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTermSettingBinding.inflate(layoutInflater)



        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)

        binding.month.setOnClickListener {
            when {
                monthSelect -> {

                }
                month3Select -> {
                    month3Select = Selected(month3Select, month3)
                }
                yearSelect -> {
                    yearSelect = Selected(yearSelect, year)
                }
                directSelect -> {
                    directSelect = noLeftSelected(directSelect, direct)
                    change_btn1.isGone = true
                    change_btn2.isGone = true
                }
            }
            monthSelect = noRightSelected(monthSelect, month)

            val cal = Calendar.getInstance()
            cal.time = Date()
            val df: DateFormat = SimpleDateFormat("yyyy.MM.dd")

            binding.endDay.text = df.format(cal.time)

            cal.add(Calendar.MONTH, -1)

            binding.startDay.text = df.format(cal.time)
        }


        binding.month3.setOnClickListener {
                  when {
                monthSelect -> {
                    monthSelect = noRightSelected(monthSelect, month)
                }
                month3Select -> {

                }
                yearSelect -> {
                    yearSelect = Selected(yearSelect, year)
                }
                directSelect -> {
                    directSelect = noLeftSelected(directSelect, direct)
                    change_btn1.isGone = true
                    change_btn2.isGone = true
                }
            }
            month3Select = Selected(month3Select, month3)

            val cal = Calendar.getInstance()
            cal.time = Date()
            val df: DateFormat = SimpleDateFormat("yyyy.MM.dd")

            binding.endDay.text = df.format(cal.time)

            cal.add(Calendar.MONTH, -3)

            binding.startDay.text = df.format(cal.time)
        }

        binding.year.setOnClickListener {
            when {
                monthSelect -> {
                    monthSelect = noRightSelected(monthSelect, month)
                }
                month3Select -> {
                    month3Select = Selected(month3Select, month3)
                }
                yearSelect -> {

                }
                directSelect -> {
                    directSelect = noLeftSelected(directSelect, direct)
                    change_btn1.isGone = true
                    change_btn2.isGone = true
                }
            }
            yearSelect = Selected(yearSelect, year)

            val cal = Calendar.getInstance()
            cal.time = Date()
            val df: DateFormat = SimpleDateFormat("yyyy.MM.dd")

            binding.endDay.text = df.format(cal.time)

            cal.add(Calendar.YEAR, -1)

            binding.startDay.text = df.format(cal.time)
        }

        binding.direct.setOnClickListener {
            when {
                monthSelect -> {
                    monthSelect = noRightSelected(monthSelect, month)
                }
                month3Select -> {
                    month3Select = Selected(month3Select, month3)
                }
                yearSelect -> {
                    yearSelect = Selected(yearSelect, year)
                }
                directSelect -> {

                }
            }
            directSelect = noLeftSelected(directSelect, direct)


            if (directSelect) {
                change_btn1.isGone = false
                change_btn2.isGone = false
            } else if (!directSelect) {
                change_btn1.isGone = true
                change_btn2.isGone = true
            }
        }

        binding.changeBtn1.setOnClickListener {
            val datepickercalendar = Calendar.getInstance()
            datepickercalendar.add(Calendar.MONTH, -12)
            val year = datepickercalendar.get(Calendar.YEAR)
            val month = datepickercalendar.get(Calendar.MONTH)
            val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    val month = monthOfYear + 1

                    binding.startDay.text = "$year.$month.$dayOfMonth"
                    picker = Calendar.getInstance().apply { set(year,month,dayOfMonth) }
                },
                year,
                month,
                day
            )
//           최소 날짜를 현재 시각 이후로

            dpd.datePicker.maxDate = System.currentTimeMillis()
            dpd.show()
        }

        binding.changeBtn2.setOnClickListener {

            val datepickercalendar = Calendar.getInstance()
            val year = datepickercalendar.get(Calendar.YEAR)
            val month = datepickercalendar.get(Calendar.MONTH)
            val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    val month = monthOfYear + 1

                    binding.endDay.text = "$year.$month.$dayOfMonth"

                },
                year,
                month,
                day
            ).apply {
            if (picker != null) {
                datePicker.minDate = picker!!.timeInMillis
            }
                datePicker.maxDate = System.currentTimeMillis()
            }
            dpd.show()
        }


        binding.ok.setOnClickListener {

        }

        binding.cancel.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    private fun noRightSelected(selected: Boolean, button: Button) : Boolean {

        var select : Boolean = selected

        if (!selected) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            button.setBackgroundResource(R.drawable.background_no_right)
            select = true

        } else if (selected) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
            button.setBackgroundResource(R.drawable.background_no_right_uncheck)
            select = false
        }
        return select

    }

    private fun noLeftSelected(selected: Boolean, button: Button) : Boolean {

        var select : Boolean = selected

        if (!selected) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            button.setBackgroundResource(R.drawable.background_no_left)
            select = true

        } else if (selected) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
            button.setBackgroundResource(R.drawable.background_no_left_uncheck)
            select = false
        }
        return select

    }

    private fun Selected(selected: Boolean, button: Button) : Boolean {

        var select : Boolean = selected

        if (!selected) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            button.setBackgroundResource(R.drawable.background_center)
            select = true

        } else if (selected) {
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
            button.setBackgroundResource(R.drawable.background_center_uncheck)
            select = false
        }
        return select

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}