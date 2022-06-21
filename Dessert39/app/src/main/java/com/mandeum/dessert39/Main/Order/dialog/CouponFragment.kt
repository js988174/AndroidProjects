package com.mandeum.dessert39.Main.Order.dialog

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentCouponBinding



class CouponFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentCouponBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomAlertDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCouponBinding.inflate(layoutInflater)

        binding.close.setOnClickListener {
            dialog?.dismiss()
        }


        val couponModel: ArrayList<CouponModel> = ArrayList()
        val rvAdapter : CouponAdapter = CouponAdapter(couponModel)
        val rv : RecyclerView = binding.couponRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        couponModel.add(CouponModel("카페모카", "1,000", "5,000원", "2021.10.10","2021.11.30"))
        couponModel.add(CouponModel("카페모카", "1,000", "5,000원", "2021.10.10","2021.11.30"))
        couponModel.add(CouponModel("카페모카", "1,000", "5,000원", "2021.10.10","2021.11.30"))
        couponModel.add(CouponModel("카페모카", "1,000", "5,000원", "2021.10.10","2021.11.30"))
        couponModel.add(CouponModel("카페모카", "1,000", "5,000원", "2021.10.10","2021.11.30"))
        couponModel.add(CouponModel("카페모카", "1,000", "5,000원", "2021.10.10","2021.11.30"))
        couponModel.add(CouponModel("카페모카", "1,000", "5,000원", "2021.10.10","2021.11.30"))


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        var size = Point()
        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 1.0).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}