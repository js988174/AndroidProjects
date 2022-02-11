package com.mandeum.dessert39.Main.Order.dialog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderCustomBinding
import kotlinx.android.synthetic.main.fragment_order_custom.*


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

        var dCaffein : Boolean = false
        var dCaffein2 : Boolean = false
        var thick : Boolean = false
        var thick2 : Boolean = false
        var pul : Boolean = false
        var pul2 : Boolean = false
        var nacota : Boolean = false
        var nacota2 : Boolean = false

//        val args: OrderCustomFragmentArgs by navArgs()
//        val no: Int = args.no
        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = shared.edit()
        val token = shared.getString("LoginToken", "")

        binding.close.setOnClickListener {
            dialog?.dismiss()
        }

        binding.dNoChange.setOnClickListener {
            if (!dCaffein2) {
                dCaffein = isSelectedBlack(dCaffein, d_no_change)
            } else if (dCaffein2) {
                dCaffein = isSelectedBlack(dCaffein, d_no_change)
                dCaffein2 = isSelectedBlack(dCaffein2, d_change)
            }
        }

        binding.dChange.setOnClickListener {
            if (!dCaffein) {
                dCaffein2 = isSelectedBlack(dCaffein2, d_change)
            } else if (dCaffein) {
                dCaffein = isSelectedBlack(dCaffein, d_no_change)
                dCaffein2 = isSelectedBlack(dCaffein2, d_change)
            }
        }

        binding.thickNormal.setOnClickListener {

        }

        binding.thickMuch.setOnClickListener {

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}