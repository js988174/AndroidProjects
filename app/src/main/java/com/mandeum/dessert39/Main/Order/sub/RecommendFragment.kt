package com.mandeum.dessert39.Main.Order.sub

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

    lateinit var binding : FragmentRecommendBinding


    val favoriteModel1 = ArrayList<OrderFavoriteItem>()
    val favoriteModel2 = ArrayList<OrderFavoriteItem2>()
    val favoriteModel3 = ArrayList<OrderFavoriteItem3>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendBinding.inflate(layoutInflater)

        binding.couponLayout.setOnClickListener {
            val coupon =  CouponFragment()
            coupon.isCancelable = false
            activity?.supportFragmentManager?.let { coupon.show(it, coupon.tag) }
        }

        val pageList: ArrayList<OrderBannerItem> = ArrayList()
        val eventItem: ArrayList<OrderEventItem> = ArrayList()
        val recommandItem: ArrayList<OrderRecommandItem> = ArrayList()
        val newItem : ArrayList<OrderNewItem> = ArrayList()
        val shopItem : ArrayList<OrderShopItem> = ArrayList()

        var rvAdapter: OrderBannerRecyclerAdapter = OrderBannerRecyclerAdapter(pageList)
        var rvAdapter2: OrderEventRecyclerAdapter = OrderEventRecyclerAdapter(eventItem)
        var rvAdapter3 : OrderRecommandRecyclerAdapter = OrderRecommandRecyclerAdapter(recommandItem)
        var rvAdapter4 : OrderNewRecyclerAdapter = OrderNewRecyclerAdapter(newItem)
        var rvAdapter5: OrderShopRecyclerAdapter = OrderShopRecyclerAdapter(shopItem)

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


        eventItem.add(OrderEventItem("https://ifh.cc/g/RwgS7v.png","https://ifh.cc/g/dBgxVz.png","딸기 순수 우유케익"))
        eventItem.add(OrderEventItem("https://ifh.cc/g/DlbrKZ.png","https://ifh.cc/g/dBgxVz.png","돌체 돌체 돌체 돌체 라떼 아이스"))
        eventItem.add(OrderEventItem("https://ifh.cc/g/RwgS7v.png","https://ifh.cc/g/dBgxVz.png","딸기"))
        eventItem.add(OrderEventItem("https://ifh.cc/g/DlbrKZ.png","https://ifh.cc/g/dBgxVz.png","딸기"))
        eventItem.add(OrderEventItem("https://ifh.cc/g/RwgS7v.png","https://ifh.cc/g/dBgxVz.png","딸기"))
        eventItem.add(OrderEventItem("https://ifh.cc/g/DlbrKZ.png","https://ifh.cc/g/dBgxVz.png","딸기"))


        recommandItem.add(OrderRecommandItem("","https://ifh.cc/g/DlbrKZ.png","https://ifh.cc/g/dBgxVz.png","허니 카페라떼"))
        recommandItem.add(OrderRecommandItem("","https://ifh.cc/g/DlbrKZ.png","https://ifh.cc/g/dBgxVz.png","돌체 돌체 돌체 돌체 라떼 아이스"))
        recommandItem.add(OrderRecommandItem("","https://ifh.cc/g/DlbrKZ.png","https://ifh.cc/g/dBgxVz.png","딸기"))
        recommandItem.add(OrderRecommandItem("","https://ifh.cc/g/DlbrKZ.png","https://ifh.cc/g/dBgxVz.png","돌체 돌체 돌체 돌체 라떼 아이스"))
        recommandItem.add(OrderRecommandItem("","https://ifh.cc/g/DlbrKZ.png","https://ifh.cc/g/dBgxVz.png","허니 카페라떼"))


        newItem.add(OrderNewItem(R.drawable.background_radius_orange, "https://ifh.cc/g/DlbrKZ.png", "그린티 엑스트라 카페"))
        newItem.add(OrderNewItem(R.drawable.background_radius_green, "https://ifh.cc/g/DlbrKZ.png", "그린티 엑스트라 카페"))
        newItem.add(OrderNewItem(R.drawable.background_radius_orange, "https://ifh.cc/g/DlbrKZ.png", "그린티 엑스트라 카페"))
        newItem.add(OrderNewItem(R.drawable.background_radius_green, "https://ifh.cc/g/DlbrKZ.png", "그린티 엑스트라 카페"))


        shopItem.add(OrderShopItem(R.drawable.background_radius_orange, R.drawable.desert1, "망고 도코룔"))
        shopItem.add(OrderShopItem(R.drawable.background_radius_orange, R.drawable.desert1, "망고 도코룔"))
        shopItem.add(OrderShopItem(R.drawable.background_radius_orange, R.drawable.desert1, "망고 도코룔"))

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



}