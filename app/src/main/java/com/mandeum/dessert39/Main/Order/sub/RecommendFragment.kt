package com.mandeum.dessert39.Main.Order.sub

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mandeum.dessert39.Main.Order.banner.OrderBannerItem
import com.mandeum.dessert39.Main.Order.banner.OrderBannerRecyclerAdapter
import com.mandeum.dessert39.Main.Order.dialog.CouponFragment
import com.mandeum.dessert39.Main.Order.favorite.OrderFavoriteItem
import com.mandeum.dessert39.Main.Order.favorite.OrderFavoriteItem2
import com.mandeum.dessert39.Main.Order.favorite.OrderFavoriteItem3
import com.mandeum.dessert39.Main.Order.slide.*
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentRecommendBinding


class RecommendFragment : Fragment() {

        private var _binding: FragmentRecommendBinding? = null
        private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendBinding.inflate(layoutInflater)

        binding.couponLayout.setOnClickListener {
            val coupon =  CouponFragment()
            coupon.isCancelable = false
            activity?.supportFragmentManager?.let { coupon.show(it, coupon.tag) }
        }


         val favoriteModel1 = ArrayList<OrderFavoriteItem>()
         val favoriteModel2 = ArrayList<OrderFavoriteItem2>()
         val favoriteModel3 = ArrayList<OrderFavoriteItem3>()
         val pageList: ArrayList<OrderBannerItem> = ArrayList()
         val eventItem: ArrayList<OrderEventItem> = ArrayList()
         val recommandItem: ArrayList<OrderRecommandItem> = ArrayList()
         val newItem: ArrayList<OrderNewItem> = ArrayList()
         val shopItem: ArrayList<OrderShopItem> = ArrayList()

        val rvAdapter = OrderBannerRecyclerAdapter(pageList)
        val rvAdapter2 = OrderEventRecyclerAdapter(eventItem)
        val rvAdapter3  = OrderRecommandRecyclerAdapter(recommandItem)
        val rvAdapter4  = OrderNewRecyclerAdapter(newItem)
        val rvAdapter5  = OrderShopRecyclerAdapter(shopItem)

        val rv : ViewPager2 = binding.viewPager1

        val rv2: RecyclerView = binding.eventMenuRecyclerView
        val rv3: RecyclerView = binding.recommandRecyclerView
        val rv4: RecyclerView = binding.newMenuRecyclerView
        val rv5: RecyclerView = binding.recommandDessertRecyclerView

        rv.adapter = rvAdapter
        rv2.adapter = rvAdapter2
        rv3.adapter = rvAdapter3
        rv4.adapter = rvAdapter4
        rv5.adapter = rvAdapter5


        pageList.add(OrderBannerItem("https://ifh.cc/g/kdYD48.png"))
        pageList.add(OrderBannerItem("https://ifh.cc/g/kdYD48.png"))
        pageList.add(OrderBannerItem("https://ifh.cc/g/kdYD48.png"))
        binding.dotsIndicator.setViewPager2(rv)


        eventItem.add(OrderEventItem("http://dessert39.com/data/product/21.png","https://ifh.cc/g/dBgxVz.png","딸기 순수 우유케익"))
        eventItem.add(OrderEventItem("http://dessert39.com/data/product/21.png","https://ifh.cc/g/dBgxVz.png","돌체 돌체 돌체 돌체 라떼 아이스"))
        eventItem.add(OrderEventItem("http://dessert39.com/data/product/21.png","https://ifh.cc/g/dBgxVz.png","딸기"))
        eventItem.add(OrderEventItem("http://dessert39.com/data/product/25.png","https://ifh.cc/g/dBgxVz.png","딸기"))
        eventItem.add(OrderEventItem("http://dessert39.com/data/product/25.png","https://ifh.cc/g/dBgxVz.png","딸기"))
        eventItem.add(OrderEventItem("http://dessert39.com/data/product/25.png","https://ifh.cc/g/dBgxVz.png","딸기"))


        recommandItem.add(OrderRecommandItem("http://dessert39.com/data/product/60.png","https://ifh.cc/g/dBgxVz.png","허니 카페라떼"))
        recommandItem.add(OrderRecommandItem("http://dessert39.com/data/product/60.png","https://ifh.cc/g/dBgxVz.png","돌체 돌체 돌체 돌체 라떼 아이스"))
        recommandItem.add(OrderRecommandItem("http://dessert39.com/data/product/60.png","https://ifh.cc/g/dBgxVz.png","딸기"))
        recommandItem.add(OrderRecommandItem("http://dessert39.com/data/product/60.png","https://ifh.cc/g/dBgxVz.png","돌체 돌체 돌체 돌체 라떼 아이스"))
        recommandItem.add(OrderRecommandItem("http://dessert39.com/data/product/60.png","https://ifh.cc/g/dBgxVz.png","허니 카페라떼"))


        newItem.add(OrderNewItem(R.drawable.background_radius_orange, "http://dessert39.com/data/product/43.png", "그린티 엑스트라 카페"))
        newItem.add(OrderNewItem(R.drawable.background_radius_green, "http://dessert39.com/data/product/43.png", "그린티 엑스트라 카페"))
        newItem.add(OrderNewItem(R.drawable.background_radius_orange, "http://dessert39.com/data/product/43.png", "그린티 엑스트라 카페"))
        newItem.add(OrderNewItem(R.drawable.background_radius_green, "http://dessert39.com/data/product/43.png", "그린티 엑스트라 카페"))


        shopItem.add(OrderShopItem(R.drawable.background_radius_orange, "http://dessert39.com/data/product/64.png", "망고 도코룔"))
        shopItem.add(OrderShopItem(R.drawable.background_radius_orange, "http://dessert39.com/data/product/64.png", "망고 도코룔"))
        shopItem.add(OrderShopItem(R.drawable.background_radius_orange,"http://dessert39.com/data/product/64.png", "망고 도코룔"))

        favoriteModel1.add(OrderFavoriteItem(R.drawable.tea1,"달고나 초코라떼 아이스","Dalgona Chocolate Latt Ice", "8.700"))
        binding.favoriteImage1.setImageResource(favoriteModel1[0].image)
        binding.favoriteTitle1.text = favoriteModel1[0].title1
        binding.favoriteContent1.text = favoriteModel1[0].title2
        binding.favoritePrice1.text = favoriteModel1[0].price

        favoriteModel2.add(OrderFavoriteItem2(R.drawable.tea1,"달고나 아이스","Dalgona Latt Ice", "9.700"))
        binding.favoriteImage2.setImageResource(favoriteModel2[0].image)
        binding.favoriteTitle2.text = favoriteModel2[0].title1
        binding.favoriteContent2.text = favoriteModel2[0].title2
        binding.favoritePrice2.text = favoriteModel2[0].price

        favoriteModel3.add(OrderFavoriteItem3(R.drawable.tea2,"달고나 초코라떼","Dalgona Chocolate  Ice", "8.700"))
        binding.favoriteImage3.setImageResource(favoriteModel3[0].image)
        binding.favoriteTitle3.text = favoriteModel3[0].title1
        binding.favoriteContent3.text = favoriteModel3[0].title2
        binding.favoritePrice3.text = favoriteModel3[0].price



        return binding.root
    }


//    override fun onPause() {
//        stopLife()
//        super.onPause()
//    }
//
//    override fun onDestroy() {
//        stopLife()
//        super.onDestroy()
//    }
//
//    override fun onDetach() {
//        stopLife()
//        super.onDetach()
//    }
//
//    private fun stopLife() {
//        pageList.clear()
//        eventItem.clear()
//        recommandItem.clear()
//        shopItem.clear()
//        newItem.clear()
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


