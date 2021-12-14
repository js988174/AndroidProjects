package com.mandeum.dessert39.Main.Order.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandeum.dessert39.Main.Order.OrderFragment
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderFindShopBinding


class OrderFindShopFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentOrderFindShopBinding? = null
    private val binding get() = _binding!!

    lateinit var rvAdapter: FindShopAdapter

    private val findShopModel = ArrayList<FindShopModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomAlertDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrderFindShopBinding.inflate(layoutInflater)



        binding.cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }

//        binding.findShop.setOnClickListener {
//            val action =
//                OrderFindShopFragmentDirections.actionOrderFindShopFragmentToOrderFragment()
//            val navHostFragment =
//                requireActivity().supportFragmentManager
//                    .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//            navHostFragment.navController.navigate(action)
//        }


        binding.findShop.setOnClickListener {
            val action = OrderFragmentDirections.actionOrderFragmentToOrderSelectShopFragment()
            findNavController().navigate(action)
            dialog?.dismiss()

        }


        rvAdapter = FindShopAdapter(requireContext(), findShopModel)
        val rv: RecyclerView = binding.locationRe
        rv.adapter = rvAdapter

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.locationRe.layoutManager = layoutManager

        rvAdapter.findShopModel.add(FindShopModel("청라호수", "인천시 서구 크리스탈로", "400M", "09:00",
            "22:00", "10:00", "23:00"))

        rvAdapter.findShopModel.add(FindShopModel("청라호수2", "인천시 서구 크리스탈로", "400M", "09:00",
            "22:00", "10:00", "23:00"))

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //this forces the sheet to appear at max height even on landscape
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}