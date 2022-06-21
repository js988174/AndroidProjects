package com.mandeum.dessert39.Main.Payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentPaymentBinding
import java.text.DateFormatSymbols


class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentBinding.inflate(layoutInflater)

        setUpSpinner()
        val spinner = mutableListOf("쿠폰을 선택해주세요.")
        val coupon = DateFormatSymbols().months
        spinner.addAll(coupon)

        binding.checkBtn.setOnClickListener {
            val payment =  PaymentBottomFragment()
            payment.isCancelable = false
            activity?.supportFragmentManager?.let { payment.show(it, payment.tag) }
        }

        binding.couponSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!binding.couponSpinner.getItemAtPosition(position).equals("쿠폰을 선택해주세요.")) {

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

       return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setUpSpinner() {
        val spinnerArray = resources.getStringArray(R.array.coupon_spinner)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.couponSpinner.adapter = adapter
    }

}