package com.mandeum.dessert39.Join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mandeum.dessert39.Find.Password.FindPw1Activity
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_join4.*
import kotlinx.android.synthetic.main.activity_join5.*

class Join4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join4)

        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        findBtn.setOnClickListener {
            val intent = Intent(this, FindPw1Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, Join3Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
    }
}