package com.mandeum.dessert39.Join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_find2.*
import kotlinx.android.synthetic.main.activity_find2.loginBtn
import kotlinx.android.synthetic.main.activity_join5.*

class Join5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join5)


        certificationBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }
    }
}