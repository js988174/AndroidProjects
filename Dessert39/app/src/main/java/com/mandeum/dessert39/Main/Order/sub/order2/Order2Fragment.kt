package com.mandeum.dessert39.Main.Order.sub.order2

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandeum.dessert39.Main.Card.animation.collapse
import com.mandeum.dessert39.Main.Card.animation.expand
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrder2Binding
import kotlinx.android.synthetic.main.fragment_card_charge.*
import kotlinx.android.synthetic.main.fragment_order2.*


class Order2Fragment : Fragment() {

    private var _binding: FragmentOrder2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrder2Binding.inflate(layoutInflater)


        binding.findImage.setOnClickListener {
            it.findNavController().navigate(R.id.action_order2Fragment_to_alarmFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

