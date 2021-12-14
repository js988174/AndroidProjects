package com.mandeum.dessert39.Main.My39.Shop.viewPager.Story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.Shop.My39ShopAdapter
import com.mandeum.dessert39.Main.My39.Shop.My39ShopModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentShopStoryBinding


class ShopStoryFragment : Fragment() {

    private var _binding: FragmentShopStoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopStoryBinding.inflate(layoutInflater)

        val storyModel: ArrayList<ShopStoryModel> = ArrayList()
        val rvAdapter : ShopStoryAdapter = ShopStoryAdapter(storyModel, requireContext())
        val rv : RecyclerView = binding.storyRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )

        storyModel.add(
            ShopStoryModel("https://ifh.cc/g/yIoiMR.png", "test", "test", "test", "https://ifh.cc/g/izh0KC.png")
        )


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}