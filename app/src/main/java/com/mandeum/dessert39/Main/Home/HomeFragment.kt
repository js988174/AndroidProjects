package com.mandeum.dessert39.Main.Home

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Login.ServerApi.Model.BannerModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.Home.Slide.HomeSliderRecyclerAdapter
import com.mandeum.dessert39.R
import com.mandeum.dessert39.Main.Home.Slide.PageItem
import com.mandeum.dessert39.Main.Home.expandable.popularityModel1
import com.mandeum.dessert39.Main.Home.expandable.popularityModel2
import com.mandeum.dessert39.Main.Home.expandable.popularityModel3
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.databinding.FragmentHomeBinding
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.concurrent.timer


class HomeFragment : Fragment() {

        private var _binding: FragmentHomeBinding? = null
        private val binding get() = _binding!!

        lateinit var rvAdapter: EventMenuAdapter
        lateinit var rvAdapter2: EventMenuAdapter2
        lateinit var rvAdapter3: HomeSliderRecyclerAdapter

        lateinit var timer : Timer
        lateinit var thread : HomeActivity
         var progressBar: Int = 8
        // handler
         var handler: Handler? = null
         lateinit var autoSelect: Runnable

        val items = ArrayList<EventMenuModel>()
        val items2 = ArrayList<EventMenuModel2>()
        val pageList = ArrayList<PageItem>()

        val todayModel = ArrayList<popularityModel1>()
        val weekModel = ArrayList<popularityModel2>()
        val monthModel = ArrayList<popularityModel3>()
        var selected : Int = 0

        private var selectTimer : Timer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(layoutInflater)
        thread = context as HomeActivity

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


        val rvAdapter = EventMenuAdapter(requireContext(), items)
        val rvAdapter2 = EventMenuAdapter2(requireContext(), items2)
        val rvAdapter3 = HomeSliderRecyclerAdapter(requireContext(), pageList)

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


        thread(start = true) {
            val bannerModel: BannerModel = ServerApi.mainBanner()

            if (bannerModel.connection) {
                if (bannerModel.errCode == "0000") {
                    thread.runOnUiThread {
                        val banner: ImageView = binding.frame1
                        Glide.with(requireContext()).load(bannerModel.banner).into(banner)
                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "connection = ${bannerModel.connection}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


        selectTimer = timer(period = 5000, initialDelay = 5000) {

            thread.runOnUiThread {

                var select = ""

                when (selected) {
                    0 -> {
                        select = "WEEK"
                    }
                    1 -> {
                        select = "MONTH"
                    }
                    2 -> {
                        select = "TODAY"
                    }
                }
                expandable(select)

            }
        }


        binding.today.setOnClickListener {
            expandable("TODAY")

        }
        binding.week.setOnClickListener {
            expandable("WEEK")

        }
        binding.month.setOnClickListener {
            expandable("MONTH")

        }


        val circleProgress : ProgressBar = binding.rewardProgressBar
        // My Reward
        circleProgress.progress = 0
        when (progressBar) {
            1 -> {
                progressBar()
            }
            2 -> {
                progressBar()
            }
            3 -> {
                progressBar()
            }
            4 -> {
                progressBar()
            }
            5 -> {
                progressBar()
            }
            6 -> {
                progressBar()
            }
            7 -> {
                progressBar()
            }
            8 -> {
                progressBar()
            }
            9 -> {
                progressBar()
            }
            10 -> {
                progressBar()
            }
            11 -> {
                progressBar()
            }
            12 -> {
                progressBar()
            }
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
                binding.week.typeface = typeface2
                binding.month.typeface = typeface2

                binding.today.setTextColor(ContextCompat.getColor(requireContext(), R.color.black2))
                binding.week.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray8))
                binding.month.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray8))

                binding.layout8.isGone = false
                binding.layout10.isGone = true
                binding.layout12.isGone = true

                selected = 0
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

                selected = 1
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

                selected = 2
            }
        }
    }




    override fun onPause() {
        super.onPause()
        stopLife()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLife()
    }

    override fun onDetach() {
        stopLife()
        super.onDetach()
    }

    private fun progressBar() {
        val view = binding.imageView9

        var count = 0

        val number1 = DecimalFormat("00")
        val a = number1.format(1)
        val b = number1.format(2)
        val c = number1.format(3)
        val d = number1.format(4)
        val e = number1.format(5)
        val f = number1.format(6)
        val g = number1.format(7)
        val h = number1.format(8)
        val i = number1.format(9)



        val rewardAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.reward_anim)

        view.startAnimation(rewardAnim)

        timer = timer(period = 15, initialDelay = 2100) {

            binding.rewardProgressBar.progress = count

            thread.runOnUiThread {
                if (count == 8) {
                binding.rewardBefore.text = a
                    if (progressBar == 1) {
                        timer.cancel()
                    } else {
                        count++
                    }
                } else if (count == 17) {
                    binding.rewardBefore.text = b
                    if (progressBar == 2) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }  else if (count == 25) {
                    binding.rewardBefore.text = c
                    if (progressBar == 3) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }   else if (count == 34) {
                    binding.rewardBefore.text = d
                    if (progressBar == 4) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 42) {
                    binding.rewardBefore.text = e
                    if (progressBar == 5) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 51) {
                    binding.rewardBefore.text = f
                    if (progressBar == 6) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 60) {
                    binding.rewardBefore.text = g
                    if (progressBar == 7) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 68) {
                    binding.rewardBefore.text = h
                    if (progressBar == 8) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 75) {
                    binding.rewardBefore.text = i
                    if (progressBar == 9) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 83) {
                    binding.rewardBefore.text = 10.toString()
                    if (progressBar == 10) {

                    } else {
                        count++
                    }
                }    else if (count == 90) {
                    binding.rewardBefore.text = 11.toString()
                    if (progressBar == 11) {
                        timer.cancel()
                    } else {
                        count++
                    }
                }   else if (count == 97) {
                    binding.rewardBefore.text = 12.toString()
                    if (progressBar == 12) {
                        timer.cancel()
                    } else {
                        count++
                    }
                } else {
                    count++
                }
                }
            }
        }
    private fun stopLife () {
        timer.cancel()
        selectTimer?.cancel()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }


