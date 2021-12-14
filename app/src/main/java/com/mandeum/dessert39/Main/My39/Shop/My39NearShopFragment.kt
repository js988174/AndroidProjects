package com.mandeum.dessert39.Main.My39.Shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Order.selectShop.select.SelectShopAdapter
import com.mandeum.dessert39.Main.Order.selectShop.select.SelectShopModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentMy39NearShopBinding


class My39NearShopFragment : Fragment() {

    private var _binding: FragmentMy39NearShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMy39NearShopBinding.inflate(layoutInflater)


        val selectShopModel: ArrayList<My39ShopModel> = ArrayList()

        val rvAdapter : My39ShopAdapter = My39ShopAdapter(selectShopModel, requireContext())
        val rv : RecyclerView = binding.my39NearRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        selectShopModel.add(
            My39ShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00", fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png")
        )

        selectShopModel.add(
            My39ShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00",  fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png")
        )

        selectShopModel.add(
            My39ShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00",  fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png")
        )

        selectShopModel.add(
            My39ShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00",  fixNo = false, fixYes = true, "https://ifh.cc/g/tpLWqM.png")
        )

        selectShopModel.add(
            My39ShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00",  fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png")
        )

        selectShopModel.add(
            My39ShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00",  fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png")
        )

        selectShopModel.add(
            My39ShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00",  fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png")
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}