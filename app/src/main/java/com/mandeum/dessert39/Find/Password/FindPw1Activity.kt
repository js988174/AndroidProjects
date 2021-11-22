package com.mandeum.dessert39.Find.Password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mandeum.dessert39.Find.Id.Find2Activity
import com.mandeum.dessert39.Find.Id.Find3Activity
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_find1.*
import kotlinx.android.synthetic.main.activity_find2.*
import kotlinx.android.synthetic.main.activity_find_pw.*
import kotlinx.android.synthetic.main.activity_find_pw.findImage

class FindPw1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pw)

        certificationBtn.setOnClickListener {
            val intent = Intent(this, FindPw2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        findImage.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
    }
}