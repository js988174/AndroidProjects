package com.mandeum.dessert39.Main.My39.Sound

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentContactBinding
import kotlinx.android.synthetic.main.fragment_contact.*


class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(layoutInflater)

        binding.kindsLayout.setOnClickListener {
            val array: Array<String> = arrayOf("포인트 문의", "쿠폰 문의", "이벤트 문의", "선물하기 문의", "회원정보 문의", "불만사항 접수", "기타")
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("결제&주문 문의")
            builder.setItems(array) { _, i ->
                when (i) {
                    0 -> {

                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                    4 -> {

                    }
                    5 -> {

                    }

                    6 -> {

                    }
                }
            }
            builder.create().show()
        }

        binding.radioButton.setOnCheckedChangeListener { _, b ->
            radio_button.isChecked = b
        }

        binding.checkBtn.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_board_add)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.findViewById<Button>(R.id.ok)?.setOnClickListener {
                dialog.dismiss()
            }

            dialog.findViewById<Button>(R.id.cancel)?.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}