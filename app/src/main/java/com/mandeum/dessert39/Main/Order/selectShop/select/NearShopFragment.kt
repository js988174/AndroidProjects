package com.mandeum.dessert39.Main.Order.selectShop.select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAidBinding
import com.mandeum.dessert39.databinding.FragmentNearShopBinding
import com.mandeum.dessert39.databinding.FragmentOrderSelectShopBinding


class NearShopFragment : Fragment() {

    private var _binding: FragmentNearShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNearShopBinding.inflate(layoutInflater)

        val selectShopModel: ArrayList<SelectShopModel> = ArrayList()

        val rvAdapter : SelectShopAdapter = SelectShopAdapter(selectShopModel, requireContext())
        val rv : RecyclerView = binding.nearRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        selectShopModel.add(SelectShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
        "09:00", "22:00", "10:00", "22:00", event = true, fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png"))

        selectShopModel.add(SelectShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00", event = true, fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png"))

        selectShopModel.add(SelectShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00", event = false, fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png"))

        selectShopModel.add(SelectShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00", event = true, fixNo = false, fixYes = true, "https://ifh.cc/g/tpLWqM.png"))

        selectShopModel.add(SelectShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00", event = false, fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png"))

        selectShopModel.add(SelectShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00", event = false, fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png"))

        selectShopModel.add(SelectShopModel("청라호수공원점","인천광역시 서구 크리스탈로 102","200m",
            "09:00", "22:00", "10:00", "22:00", event = false, fixNo = true, fixYes = false, "https://ifh.cc/g/tpLWqM.png"))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}