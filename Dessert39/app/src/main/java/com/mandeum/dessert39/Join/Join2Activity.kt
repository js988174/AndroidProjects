package com.mandeum.dessert39.Join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_join2.*


class Join2Activity : AppCompatActivity() {

    private var check1 : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join2)

        val check1 : Boolean = intent.getBooleanExtra("checkBox1", false)


        findImage.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            intent.putExtra("checkBox1", check1)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, JoinActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        intent.putExtra("checkBox1", check1)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
    }
}