package com.mandeum.dessert39.Main.Order.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderCustomBinding


class OrderCustomFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentOrderCustomBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomAlertDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentOrderCustomBinding.inflate(layoutInflater)

        binding.close.setOnClickListener {
            dialog?.dismiss()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}