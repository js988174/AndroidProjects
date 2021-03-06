package com.mandeum.dessert39.Main.Order.sub.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderMenuDetailBinding
import com.mandeum.dessert39.databinding.FragmentOrderMenuInfoBinding


class OrderMenuInfoFragment : Fragment() {

    private var _binding : FragmentOrderMenuInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrderMenuInfoBinding.inflate(layoutInflater)

//        val dessertImage : ImageView = binding.dessertImage
//
//        val args: OrderMenuInfoFragmentArgs by navArgs()
//
//        val imageView = args.imageView1

//        binding.apply {
//            Glide.with(requireContext()).load(imageView).into(dessertImage)
//        }

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val count : TextView = binding.basketNumber

//        if (count.equals(1)) {
//            binding.basketMinus1.setImageResource(R.drawable.minus_gray)
//        } else {
//            binding.basketMinus1.setImageResource(R.drawable.minus_black)
//        }



        binding.basketPlus.setOnClickListener {
            val countNumber : Int = count.text.toString().toInt()
            count.text = "${countNumber + 1}"

        }

        binding.basketMinus.setOnClickListener {
            val countNumber : Int = count.text.toString().toInt()
            if ( countNumber > 1) {
                count.text = "${countNumber - 1}"
            }
        }


        binding.cartBtn.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.CustomAlertDialog)
            bottomSheetDialog.setContentView(R.layout.dialog_menu_plus)
            bottomSheetDialog.setCanceledOnTouchOutside(false)
            bottomSheetDialog.setCancelable(false)
            bottomSheetDialog.findViewById<ImageView>(R.id.close_btn)?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.findViewById<Button>(R.id.order_go)?.setOnClickListener {
                val action = OrderMenuInfoFragmentDirections.actionOrderMenuInfoFragmentToOrderCartFragment()
                findNavController().navigate(action)
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.findViewById<Button>(R.id.order_ok)?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}