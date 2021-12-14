package com.mandeum.dessert39.Main

import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.mandeum.dessert39.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import java.security.MessageDigest
import java.util.*
import java.util.Base64 as Base641


class HomeActivity : AppCompatActivity() {

    companion object {
       private lateinit var binding: ActivityHomeBinding

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   setContentView(R.layout.activity_main)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//            activity = this
//        val view: View = layoutInflater.inflate(R.layout.bottom_sheet_home, null)
//        val dialog = BottomSheetDialog(this)
//        dialog.setContentView(view)
//        dialog.show()



    }


//    companion object {
//        var activity: HomeActivity? = null
//    }

}