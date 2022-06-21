package com.mandeum.dessert39.Main.My39.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.History.model.Order2MenuAdapter
import com.mandeum.dessert39.Main.My39.History.model.Order2MenuModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderHistoryDetailBinding
import kotlinx.android.synthetic.main.fragment_order_history_detail.*


class OrderHistoryDetailFragment : Fragment() {

    private var _binding: FragmentOrderHistoryDetailBinding? = null
    private val binding get() = _binding!!

    var clickScroll : Boolean = false
    var takeSelected : Boolean = false
    var forSelected : Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentOrderHistoryDetailBinding.inflate(layoutInflater)

        val location : TextView = binding.locationText

        val args: OrderHistoryDetailFragmentArgs by navArgs()

        binding.apply {
            location.text = args.location
        }

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val order2MenuModel: ArrayList<Order2MenuModel> = ArrayList()
        val rvAdapter : Order2MenuAdapter = Order2MenuAdapter(order2MenuModel, requireContext())
        val rv : RecyclerView = binding.orderRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        binding.checkBtn.setOnClickListener {
            val customOption =  ReviewFragment()
            customOption.isCancelable = false
            activity?.supportFragmentManager?.let { customOption.show(it, customOption.tag) }
        }

        order2MenuModel.add(
            Order2MenuModel( "달고나"
            ,"ICED","GRANDE","개인컵","6.700","샷추가+1",
            "물적게","얼음 많이","500","2","25.300")
        )

        order2MenuModel.add(Order2MenuModel( "달고나"
            ,"ICED","GRANDE","개인컵","6.700","샷추가+1",
            "물적게","얼음 많이","500","2","25.300"))

        order2MenuModel.add(Order2MenuModel( "달고나"
            ,"ICED","GRANDE","개인컵","6.700","샷추가+1",
            "물적게","얼음 많이","500","2","25.300"))

        order2MenuModel.add(Order2MenuModel( "달고나"
            ,"ICED","GRANDE","개인컵","6.700","샷추가+1",
            "물적게","얼음 많이","500","2","25.300"))


        val viewModel = binding.viewGray3
        val updown : ConstraintLayout = binding.up


        updown.setOnClickListener {
            if (!clickScroll) {
                order_up1.setImageResource(R.drawable.down)
                order_recyclerView.isGone = true
                viewModel.isGone = true
                clickScroll = true
                upText1.isVisible = false
                upText3.isVisible = true
            } else if (clickScroll) {
                order_up1.setImageResource(R.drawable.up)
                order_recyclerView.isGone = false
                clickScroll = false
                viewModel.isGone = false
                upText1.isVisible = true
                upText3.isVisible = false
            }
        }



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}