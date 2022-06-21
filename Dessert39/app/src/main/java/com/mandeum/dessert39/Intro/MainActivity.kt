package com.mandeum.dessert39.Intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.common.util.Utility
import com.mandeum.dessert39.Intro.Onboarding.OnboardingAdapter
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "로그"
    }

    private var isDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        my_intro_view_pager.adapter = OnboardingAdapter(supportFragmentManager)
        indicator.setViewPager(my_intro_view_pager)

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