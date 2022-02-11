package com.mandeum.dessert39.Main.Order.sub

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mandeum.dessert39.Login.ServerApi.Model.Order.*
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.banner.OrderBannerRecyclerAdapter
import com.mandeum.dessert39.Main.Order.dialog.CouponFragment
import com.mandeum.dessert39.Main.Order.favorite.OrderFavoriteItem
import com.mandeum.dessert39.Main.Order.favorite.OrderFavoriteItem2
import com.mandeum.dessert39.Main.Order.favorite.OrderFavoriteItem3
import com.mandeum.dessert39.Main.Order.slide.*
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentRecommendBinding
import kotlin.concurrent.thread


class RecommendFragment : Fragment() {

        private var _binding: FragmentRecommendBinding? = null
        private val binding get() = _binding!!
        lateinit var thread : HomeActivity
        private var condition : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        binding.couponLayout.setOnClickListener {
            val coupon =  CouponFragment()
            coupon.isCancelable = false
            activity?.supportFragmentManager?.let { coupon.show(it, coupon.tag) }
        }


         val favoriteModel1 = ArrayList<OrderFavoriteItem>()
         val favoriteModel2 = ArrayList<OrderFavoriteItem2>()
         val favoriteModel3 = ArrayList<OrderFavoriteItem3>()
         val eventItem: ArrayList<OrderEventItem> = ArrayList()


        val rvAdapter2 = OrderEventRecyclerAdapter(eventItem)
        val rv2: RecyclerView = binding.eventMenuRecyclerView

        rv2.adapter = rvAdapter2




        thread(start = true) {
            val orderBanner: OrderBannerModel = ServerApi.orderBanner()

            if (orderBanner.connection) {
                if (orderBanner.errCode == "0000") {
                    thread.runOnUiThread {
                        val rv: ViewPager2 = binding.viewPager1
                        rv.adapter = OrderBannerRecyclerAdapter(orderBanner.Arraylist)
                        binding.dotsIndicator.setViewPager2(rv)
                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "connection = ${orderBanner.connection}\n연결 실패",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            val newItem: ArrayList<OrderNewItem> = ArrayList()
            val newMenu: NewMenuModel = ServerApi.newMenu(token.toString())

            if (newMenu.connection) {
                if (newMenu.errCode == "0000") {
                    thread.runOnUiThread {

                        val rv4: RecyclerView = binding.newMenuRecyclerView
                        rv4.adapter = OrderNewRecyclerAdapter(newMenu.list, requireContext())
                        rv4.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )

                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "connection = ${newMenu.connection}\n연결 실패",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            val seasonMenu: SeasonMenuModel = ServerApi.seasonMenu(token.toString())
            val seaonModel =
                SeasonMenuModel(seasonMenu.connection, seasonMenu.errCode, seasonMenu.list)

            Log.d("확인", seasonMenu.list.toString())
            if (seasonMenu.connection) {
                if (seasonMenu.errCode == "0000") {
                    thread.runOnUiThread {
                        val rv5: RecyclerView = binding.season1Laout
                        rv5.adapter =
                            OrderSeasonRecyclerAdapter(seasonMenu.list, requireContext())
                        rv5.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                        Log.d("확인", seasonMenu.list.toString())
                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "connection = ${seasonMenu.connection}\n연결 실패",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("확인", seasonMenu.list.toString())
                }
            }


            val recommandMenu: RecommandMenuModel = ServerApi.recommandMenu(token.toString())

            if (recommandMenu.connection) {
                if (recommandMenu.errCode == "0000") {
                    thread.runOnUiThread {
                        val rv5: RecyclerView = binding.recommandDessertRecyclerView
                        rv5.adapter = OrderShopRecyclerAdapter(recommandMenu.list)
                        rv5.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )

                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "connection = ${recommandMenu.connection}\n연결 실패",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            val adminMenu: AdminRecommandModel = ServerApi.adminRecommandMenu(token.toString())

            if (adminMenu.connection) {
                if (adminMenu.errCode == "0000") {
                    thread.runOnUiThread {
                        val rv3: RecyclerView = binding.recommandRecyclerView
                        rv3.adapter = OrderRecommandRecyclerAdapter(adminMenu.list)
                        rv3.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )

                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "connection = ${adminMenu.connection}\n연결 실패",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }


        eventItem.add(OrderEventItem("http://dessert39.com/data/product/21.png","https://ifh.cc/g/dBgxVz.png","딸기 순수 우유케익"))
        eventItem.add(OrderEventItem("http://dessert39.com/data/product/21.png","https://ifh.cc/g/dBgxVz.png","돌체 돌체 돌체 돌체 라떼 아이스"))





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


    override fun onPause() {
        super.onPause()
        condition = false
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


