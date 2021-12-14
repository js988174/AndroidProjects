package com.mandeum.dessert39.Main.My39.History

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandeum.dessert39.Main.Order.sub.detail.OrderMenuDetailFragmentDirections
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderHistoryBinding
import com.mandeum.dessert39.databinding.FragmentReviewBinding


class ReviewFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomAlertDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentReviewBinding.inflate(layoutInflater)

        binding.close.setOnClickListener {
            dialog?.dismiss()
        }

        binding.checkBtn.setOnClickListener {
            dialog?.dismiss()
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_review_completion)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.findViewById<Button>(R.id.ok)?.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        val reviewModel : ArrayList<ReviewItem> = ArrayList()

        val rvAdapter = ReviewAdapter(requireContext(), reviewModel)

        val rv : RecyclerView = binding.reviewRecyclerView

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        reviewModel.add(ReviewItem("아메리카노"))

        reviewModel.add(ReviewItem("아메리카노"))

        reviewModel.add(ReviewItem("아메리카노"))

        reviewModel.add(ReviewItem("아메리카노"))



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}