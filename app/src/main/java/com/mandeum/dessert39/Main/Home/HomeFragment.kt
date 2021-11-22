package com.mandeum.dessert39.Main.Home

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mandeum.dessert39.Main.Home.Slide.HomeSliderRecyclerAdapter
import com.mandeum.dessert39.R
import com.mandeum.dessert39.Main.Home.Slide.PageItem
import com.mandeum.dessert39.Main.Home.expandable.popularityModel1
import com.mandeum.dessert39.Main.Home.expandable.popularityModel2
import com.mandeum.dessert39.Main.Home.expandable.popularityModel3
import com.mandeum.dessert39.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

        lateinit var binding: FragmentHomeBinding
        lateinit var rvAdapter: EventMenuAdapter
        lateinit var rvAdapter2: EventMenuAdapter2
        lateinit var rvAdapter3: HomeSliderRecyclerAdapter

        val items = ArrayList<EventMenuModel>()
        val items2 = ArrayList<EventMenuModel2>()
        val pageList = ArrayList<PageItem>()

        val todayModel = ArrayList<popularityModel1>()
        val weekModel = ArrayList<popularityModel2>()
        val monthModel = ArrayList<popularityModel3>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
//        binding.rewardProgressBar.progress = 80

        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_alarmFragment)
        }
        binding.navMy39.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_orderFragment)
        }


        rvAdapter = EventMenuAdapter(requireContext(), items)
        rvAdapter2 = EventMenuAdapter2(requireContext(), items2)
        rvAdapter3 = HomeSliderRecyclerAdapter(requireContext(), pageList)

        val rv : RecyclerView = binding.recyclerView
        val rv2 : RecyclerView = binding.recyclerView3
        val rv3 : ViewPager2 = binding.viewPager1

        rv.adapter =  rvAdapter
        rv2.adapter = rvAdapter2
        rv3.adapter = rvAdapter3

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager2 = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView3.layoutManager = layoutManager2

        val items = ArrayList<EventMenuModel>()
        rvAdapter.items.add(EventMenuModel("허니 카페라떼", R.drawable.tea1, R.drawable.best_white))
        rvAdapter.items.add(EventMenuModel("돌체라떼 아이스", R.drawable.tea2, R.drawable.best))
        rvAdapter.items.add(EventMenuModel("허니 카페라떼", R.drawable.tea1, R.drawable.best_white))
        rvAdapter.items.add(EventMenuModel("돌체라떼 아이스", R.drawable.tea2, R.drawable.best_white))
        rvAdapter.items.add(EventMenuModel("커피1", R.drawable.tea2, R.drawable.best))
        rvAdapter.items.add(EventMenuModel("커피1", R.drawable.tea2, R.drawable.best_white))

        val items2 = ArrayList<EventMenuModel2>()
        rvAdapter2.items2.add(EventMenuModel2(R.drawable.banner1))
        rvAdapter2.items2.add(EventMenuModel2(R.drawable.banner2))
        rvAdapter2.items2.add(EventMenuModel2(R.drawable.banner1))


        val pageList = ArrayList<PageItem>()

        rvAdapter3.pageList.add(PageItem(R.drawable.background_radius_red_light, R.drawable.desert4, "Brownie ice cream","오늘의 날씨와 잘 어울리는\n달콤한 브라우니 아이스크림"))
        rvAdapter3.pageList.add(PageItem(R.drawable.background_radius_blue, R.drawable.desert4, "Brownie ice cream23","오늘의 날씨와 잘 어울리는\n달콤한"))
        rvAdapter3.pageList.add(PageItem(R.drawable.background_radius_blue, R.drawable.desert4, "Brownie ice cream333", "오늘의 날씨와 잘 어울리는\n달콤한 브라우니 아이스크림 222"))
        binding.dotsIndicator.setViewPager2(rv3)


        todayModel.add(popularityModel1(R.drawable.desert1, "버블 흑당 밀크디", "Bubble Black Sugar Milk Tea"))
        todayModel.add(popularityModel1(R.drawable.desert2, "볼케이노 스트로베리 프로마쥬 빙수", "Volcano Strawberry Cheese Shaved-Ice"))
        todayModel.add(popularityModel1(R.drawable.desert3, "녹차 오믈렛 (딸기)", "Green Tea Omelet StrawBerry"))

        binding.todayTea1.setImageResource(todayModel[0].image)
        binding.todayTea2.setImageResource(todayModel[1].image)
        binding.todayTea3.setImageResource(todayModel[2].image)

        binding.todayTea1Title.text = todayModel[0].title1
        binding.todayTea2Title.text = todayModel[1].title1
        binding.todayTea3Title.text = todayModel[2].title1

        binding.todayTea1Content.text = todayModel[0].title2
        binding.todayTea2Content.text = todayModel[1].title2
        binding.todayTea3Content.text = todayModel[2].title2

        weekModel.add(popularityModel2(R.drawable.desert1, "버블 흑당 밀크디", "Bubble Black Sugar Milk Tea"))
        weekModel.add(popularityModel2(R.drawable.desert2, "볼케이노 스트로베리 프로마쥬 빙수", "Volcano Strawberry Cheese Shaved-Ice"))
        weekModel.add(popularityModel2(R.drawable.desert3, "녹차 오믈렛 (딸기)", "Green Tea Omelet StrawBerry"))

        binding.weekTea1.setImageResource(weekModel[0].image)
        binding.weekTea2.setImageResource(weekModel[1].image)
        binding.weekTea3.setImageResource(weekModel[2].image)

        binding.weekTea1Title.text = weekModel[0].title1
        binding.weekTea2Title.text = weekModel[1].title1
        binding.weekTea3Title.text = weekModel[2].title1

        binding.weekTea1Content.text = weekModel[0].title2
        binding.weekTea2Content.text = weekModel[1].title2
        binding.weekTea3Content.text = weekModel[2].title2

        monthModel.add(popularityModel3(R.drawable.desert1, "버블 흑당 밀크디", "Bubble Black Sugar Milk Tea"))
        monthModel.add(popularityModel3(R.drawable.desert2, "볼케이노 스트로베리 프로마쥬 빙수", "Volcano Strawberry Cheese Shaved-Ice"))
        monthModel.add(popularityModel3(R.drawable.desert3, "녹차 오믈렛 (딸기)", "Green Tea Omelet StrawBerry"))

        binding.monthTea1.setImageResource(monthModel[0].image)
        binding.monthTea2.setImageResource(monthModel[1].image)
        binding.monthTea3.setImageResource(monthModel[2].image)

        binding.monthTea1Title.text = monthModel[0].title1
        binding.monthTea2Title.text = monthModel[1].title1
        binding.monthTea3Title.text = monthModel[2].title1

        binding.monthTea1Content.text = monthModel[0].title2
        binding.monthTea2Content.text = monthModel[1].title2
        binding.monthTea3Content.text = monthModel[2].title2

        binding.today.setOnClickListener {
            expandable("TODAY")
        }
        binding.week.setOnClickListener {
            expandable("WEEK")
        }
        binding.month.setOnClickListener {
            expandable("MONTH")
        }


        return binding.root
    }

    private fun expandable(choice : String) {
        when(choice) {
            "TODAY" -> {
                binding.today.text = "TODAY"
                binding.week.text = "WEEK"
                binding.month.text = "MONTH"

                val typeface = Typeface.createFromAsset(requireContext().assets, "lexend_bold.ttf")
                binding.today.typeface = typeface

                val typeface2 = Typeface.createFromAsset(requireContext().assets, "lexend_light.ttf")
                binding.week.typeface = typeface
                binding.month.typeface = typeface

                binding.today.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                binding.week.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray8))
                binding.month.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray8))

                binding.layout8.isGone = false
                binding.layout10.isGone = true
                binding.layout12.isGone = true
            }
            "WEEK" -> {
                binding.today.text = "TODAY"
                binding.week.text = "WEEK"
                binding.month.text = "MONTH"

                val typeface : Typeface = Typeface.createFromAsset(requireContext().assets, "lexend_bold.ttf")
                binding.week.typeface = typeface

                val typeface2 = Typeface.createFromAsset(requireContext().assets, "lexend_light.ttf")
                binding.today.typeface = typeface2
                binding.month.typeface = typeface2

                binding.today.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray8))
                binding.week.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                binding.month.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray8))

                binding.layout8.isGone = true
                binding.layout10.isGone = false
                binding.layout12.isGone = true
            }
            "MONTH" -> {
                binding.today.text = "TODAY"
                binding.week.text = "WEEK"
                binding.month.text = "MONTH"

                val typeface = Typeface.createFromAsset(requireContext().assets, "lexend_bold.ttf")
                binding.month.typeface = typeface


                val typeface2 = Typeface.createFromAsset(requireContext().assets, "lexend_light.ttf")
                binding.week.typeface = typeface2
                binding.today.typeface = typeface2

                binding.today.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray8))
                binding.week.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray8))
                binding.month.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))

                binding.layout8.isGone = true
                binding.layout10.isGone = true
                binding.layout12.isGone = false
            }
        }
    }

//    override fun onClick(p0: View?) {
//        p0?.let {
//            when (it.id) {
//                R.id.layout8, R.id.layout10, R.id.layout12 -> {
//                    if (layout8.visibility == View.GONE || layout10.visibility == View.GONE || layout12.visibility == View.GONE) {
//                        layout8.expand(scrollView = nested_scroll_view)
//                        layout10.expand(scrollView = nested_scroll_view)
//                        layout12.expand(scrollView = nested_scroll_view)
//                    } else { //VISIBLE
//                        layout8.collapse()
//                        layout10.collapse()
//                        layout12.collapse()
//
//                    }
//                }
//            }
//        }
//    }


}
