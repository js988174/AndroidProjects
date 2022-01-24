package com.mandeum.dessert39.Main

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.mandeum.dessert39.Login.ServerApi.Model.Login.LikingModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.fragment_order_menu_detail.*
import kotlin.concurrent.thread


class HomeActivity : AppCompatActivity() {

    companion object {
       private lateinit var binding: ActivityHomeBinding

    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val navHost = supportFragmentManager.findFragmentById(R.id.nav_fragment)
//        navHost?.let { navFragment ->
//            navFragment.childFragmentManager.primaryNavigationFragment?.let {fragment->
//                //DO YOUR STUFF
//            }
//        }

    }
}