package com.mandeum.dessert39.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.RoundedCornerTreatment
import com.mandeum.dessert39.Main.Home.HomeFragment
import com.mandeum.dessert39.Main.Order.OrderFragment
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   setContentView(R.layout.activity_main)

        binding = ActivityHomeBinding.inflate(layoutInflater)


        val view: View = layoutInflater.inflate(R.layout.bottom_sheet_home, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()

        setContentView(binding.root)

    }


}