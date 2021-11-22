package com.mandeum.dessert39.Main.My39.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderHistoryBinding


class OrderHistoryFragment : Fragment() {

    lateinit var binding: FragmentOrderHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderHistoryBinding.inflate(layoutInflater)




        return binding.root
    }


}