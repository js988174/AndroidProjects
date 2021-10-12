package com.mandeum.dessert39.Intro

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.Main.Home.Slide.HomeSliderRecyclerAdapter
import com.mandeum.dessert39.Main.Home.Slide.PageItem
import com.mandeum.dessert39.R
import com.mandeum.dessert39.retrofit2.ImageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "로그"
    }

    private var pageItemList = ArrayList<IntroPageItem>()
    private lateinit var introSlideAdapter: IntroSlideAdapter
    private var isDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        pageItemList.add(IntroPageItem(R.color.colorOrange, R.drawable.ic_pager_item_1, "디저트 39앱 !"))
        pageItemList.add(IntroPageItem(R.color.colorBlue, R.drawable.ic_launcher_foreground, "많은 이용 부탁드립니다."))
        pageItemList.add(IntroPageItem(R.color.colorWhite, R.drawable.ic_launcher_background, "많이 이용해주세요!"))

        introSlideAdapter = IntroSlideAdapter(pageItemList)

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        // 뷰페이저에 설정
        my_intro_view_pager.apply {

            adapter = introSlideAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL

            this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorBlue)))
                }

            })

            dots_indicator.setViewPager2(this)
        }

        // 로그인 버튼
        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

        // 회원가입 버튼
        joinBtn.setOnClickListener {
            val intent = Intent(this, ImageActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }


    }

    override fun onBackPressed() {
        if(isDouble == true) {
            finish()
        }
        isDouble = true
        Toast.makeText(this, "종료하시려면 더블클릭", Toast.LENGTH_LONG).show()
        Handler().postDelayed(Runnable {isDouble = false}, 2000)

    }
}