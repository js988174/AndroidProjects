package com.mandeum.dessert39.Main.Order.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderShopDetailBinding


class OrderShopDetailFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentOrderShopDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomAlertDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderShopDetailBinding.inflate(layoutInflater)



        return binding.root
    }


}