package com.mandeum.dessert39.Main.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.mandeum.dessert39.Main.Home.expandable.CategoryAdapter
import com.mandeum.dessert39.Main.Home.expandable.CategoryList
import com.mandeum.dessert39.R
import com.mandeum.dessert39.Main.Home.Slide.HomeSliderRecyclerAdapter
import com.mandeum.dessert39.Main.Home.Slide.PageItem
import com.mandeum.dessert39.databinding.FragmentHomeBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.dots_indicator


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding


   private val categoryList = ArrayList<CategoryList>()

    lateinit var  rvAdapter: EventMenuAdapter
    lateinit var  rvAdapter2: EventMenuAdapter2
    lateinit var  rvAdapter3: HomeSliderRecyclerAdapter

    val items = ArrayList<EventMenuModel>()
    val items2 = ArrayList<EventMenuModel2>()
    val pageList = ArrayList<PageItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

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
        rvAdapter.items.add(EventMenuModel("허니 카페라떼", R.drawable.coffee1, R.drawable.background1))
        rvAdapter.items.add(EventMenuModel("돌체라떼 아이스", R.drawable.coffee1, R.drawable.background1))
        rvAdapter.items.add(EventMenuModel("허니 카페라떼", R.drawable.coffee1, R.drawable.background1))
        rvAdapter.items.add(EventMenuModel("돌체라떼 아이스", R.drawable.coffee1, R.drawable.background1))
        rvAdapter.items.add(EventMenuModel("커피1", R.drawable.coffee1, R.drawable.background1))
        rvAdapter.items.add(EventMenuModel("커피1", R.drawable.coffee1, R.drawable.background1))

        val items2 = ArrayList<EventMenuModel2>()
        rvAdapter2.items2.add(EventMenuModel2(R.drawable.banner3))
        rvAdapter2.items2.add(EventMenuModel2(R.drawable.banner3))
        rvAdapter2.items2.add(EventMenuModel2(R.drawable.banner3))


        val pageList = ArrayList<PageItem>()

        rvAdapter3.pageList.add(PageItem(R.color.colorPrimary, R.drawable.ic_pager_item_1, "디저트 39앱 !"))
        rvAdapter3.pageList.add(PageItem(R.color.colorBlue, R.drawable.ic_launcher_foreground, "많은 이용 부탁드립니다."))
        rvAdapter3.pageList.add(PageItem(R.color.colorWhite, R.drawable.ic_launcher_background, "많이 이용해주세요!"))


        binding.dotsIndicator.setViewPager2(rv3)




//        initData()
//        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        val categoryAdapter = CategoryAdapter(categoryList)
        recyclerView2.adapter = categoryAdapter
        recyclerView2.setHasFixedSize(true)
    }

    private fun initData() {
        categoryList.add(
            CategoryList(
            "Today" ,"버블 흑당 밀크티", "Bubble Black suger Milk Tea", R.drawable.coffee1

        )
        )

        categoryList.add(CategoryList(
            "Week" ,"볼케이노 스트로베리 프로마쥬 빙수", "Volcano Strawberry Promage\n" +
                    "Shaved-ice", R.drawable.coffee1
        )
        )
        categoryList.add(CategoryList(
            "MONTH" ,"녹차 오믈렛(딸기)", "ㅇㅇ", R.drawable.coffee1
        )
        )
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}
