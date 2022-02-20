package com.mandeum.dessert39.Main.My39.Sound

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mandeum.dessert39.Login.ServerApi.Model.Board.SetBoardModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentContactBinding
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlin.concurrent.thread


class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!
    lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken",  "")

        binding.kindsLayout.setOnClickListener {
            val array: Array<String> = arrayOf("결제&주문", "포인트", "쿠폰", "이벤트", "선물하기", "회원정보", "불만사항 접수", "기타")
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("결제&주문 문의")
            builder.setItems(array) { _, i ->
                when (i) {
                    0 -> {
                        binding.title.text = "결제&주문"
                    }
                    1 -> {
                        binding.title.text = "포인트"
                    }
                    2 -> {
                        binding.title.text = "쿠폰"
                    }
                    3 -> {
                        binding.title.text = "이벤트"
                    }
                    4 -> {
                        binding.title.text = "선물하기"
                    }
                    5 -> {
                        binding.title.text = "회원정보"
                    }

                    6 -> {
                        binding.title.text = "불만사항 접수"
                    }
                    7 -> {
                        binding.title.text = "기타"
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
                boardApi(token.toString(), category = binding.title.text,
                    titleArea,contentArea, titleArea.text.toString(), contentArea.text.toString())
                dialog.dismiss()
            }

            dialog.findViewById<Button>(R.id.cancel)?.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }


        return binding.root
    }

    private fun boardApi(token: String, category: CharSequence, titleArea: EditText, contentArea: EditText, title: String, content: String ) {
        thread(start = true) {
            val boardModel: SetBoardModel = ServerApi.setBoard(token.toString(),
                category.toString(),title, content )

            if (boardModel.connection) {
                if (boardModel.errCode == "0000") {
                    thread.runOnUiThread {
                        Toast.makeText(requireContext(), "등록했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "${boardModel.connection}오류",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}