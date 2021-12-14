package com.mandeum.dessert39.Main.My39.Shop.viewPager.Information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.mandeum.dessert39.databinding.FragmentShopInformationBinding
import kotlinx.android.synthetic.main.fragment_shop_information.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class ShopInformationFragment : Fragment() {

    private var _binding: FragmentShopInformationBinding? = null
    private val binding get() = _binding!!


     lateinit var viewPagerAdapter: InformationViewPager

     lateinit var viewModel: InformationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopInformationBinding.inflate(layoutInflater)

//        val mapView = MapView(requireActivity())
//        binding.clKakaoMapView.addView(mapView)
//
//        // 역삼동에서 한 주소
//
//        //37.57949781081026, 37.57949781081026
//        val mapPoint = MapPoint.mapPointWithGeoCoord(37.57949781081026, 37.57949781081026)
//
//        mapView.setMapCenterPoint(mapPoint, true)
//        mapView.setZoomLevel(1, true)

//        val marker = MapPOIItem()
//        marker.itemName = "강남"
//        marker.mapPoint = mapPoint
//        marker.markerType = MapPOIItem.MarkerType.BluePin
//        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
//
//        mapView.addPOIItem(marker)

        viewModel = ViewModelProvider(this).get(InformationViewModel::class.java)
        viewModel.setBannerItems(
            listOf(
                BannerItem("https://ifh.cc/g/xGQ0Te.jpg"),
                BannerItem("https://ifh.cc/g/xGQ0Te.jpg"),
                BannerItem("https://ifh.cc/g/xGQ0Te.jpg"),
                BannerItem("https://ifh.cc/g/xGQ0Te.jpg"),
                BannerItem("https://ifh.cc/g/xGQ0Te.jpg"),
                BannerItem("https://ifh.cc/g/xGQ0Te.jpg")
            )
        )

        initViewPager2()
        subscribeObservers()

        return binding.root
    }

    private fun initViewPager2() {

        val viewPager2: androidx.viewpager2.widget.ViewPager2 = binding.viewPager2


        viewPager2.apply {
            viewPagerAdapter = InformationViewPager()
            adapter = viewPagerAdapter
            viewPager2.registerOnPageChangeCallback(object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    page_number.text = "${position+1}"
                }
            })
        }
    }

    private fun subscribeObservers() {
        viewModel.bannerItemList.observe(requireActivity(), Observer { bannerItemList ->
            viewPagerAdapter.submitList(bannerItemList)
        })

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}