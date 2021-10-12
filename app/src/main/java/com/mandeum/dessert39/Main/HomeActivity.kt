package com.mandeum.dessert39.Main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.RoundedCornerTreatment
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.Main.Home.HomeFragment
import com.mandeum.dessert39.Main.Order.OrderFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   setContentView(R.layout.activity_main)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottom_nav.background = null
        bottom_nav.menu.getItem(4).isEnabled = false

        loadFragment(HomeFragment.newInstance())

        val bottomAppBar = binding.bottomBar
        val bottomBarBackground = bottomAppBar.background as MaterialShapeDrawable
        bottomBarBackground.shapeAppearanceModel = bottomBarBackground.shapeAppearanceModel
            .toBuilder()
            .setTopLeftCorner(RoundedCornerTreatment())
            .setTopLeftCornerSize(RelativeCornerSize(0.7f))
            .build()

        Handler().postDelayed({
            badgeSetup(R.id.nav_alarm, 1)
        },2000)


        bottom_nav.setOnItemSelectedListener { item ->
            var fragment: Fragment
            when (item.itemId) {
                R.id.nav_home -> {
                    fragment = HomeFragment()
                    loadFragment(fragment)
                    badgeClear(R.id.nav_home)
                    true
                }
                R.id.nav_card -> {
                    fragment = CardFragment()
                    loadFragment(fragment)
                    badgeClear(R.id.nav_card)
                    true

                }
                R.id.nav_alarm -> {
                    fragment = AlarmFragment()
                    loadFragment(fragment)
                    badgeClear(R.id.nav_alarm)
                    true

                }
                R.id.nav_profile -> {
                    fragment = OrderFragment()
                    loadFragment(fragment)
                    badgeClear(R.id.nav_profile)
                    true

                }

                R.id.fab -> {
                    fragment = ProfileFragment()
                    loadFragment(fragment)
                    badgeClear(R.id.blank)
                    true
                }

                else -> false
            }

        }


    }

    private fun badgeSetup(id: Int, alerts: Int) {
        val badge = bottom_nav.getOrCreateBadge(id)
        badge.isVisible = true
        badge.number = alerts
    }

    private fun badgeClear(id: Int) {
        val badgeDrawable = bottom_nav.getBadge(id)
        if (badgeDrawable != null) {
            badgeDrawable.isVisible = false
            badgeDrawable.clearNumber()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}