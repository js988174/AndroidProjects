package com.mandeum.dessert39.Join

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.lifecycle.ViewModelProvider
import com.mandeum.dessert39.Find.Id.Find3Activity
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_find2.*
import kotlinx.android.synthetic.main.activity_find_pw.*
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_join.findImage


class JoinActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)


        allCheckBtn.setOnClickListener { onCheckChanged(allCheckBtn) }
        firstCheckBtn.setOnClickListener { onCheckChanged(firstCheckBtn) }
        secondCheckBtn.setOnClickListener { onCheckChanged(secondCheckBtn) }
        thirdCheckBtn.setOnClickListener { onCheckChanged(thirdCheckBtn) }
        fourthCheckBtn.setOnClickListener { onCheckChanged(fourthCheckBtn) }
        fifthCheckBtn.setOnClickListener { onCheckChanged(fourthCheckBtn) }

        nextBtn.isEnabled = false

        checkImage1.setOnClickListener {
            val intent = Intent(this, Join2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        checkImage2.setOnClickListener {
            val intent = Intent(this, Join2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        checkImage3.setOnClickListener {
            val intent = Intent(this, Join2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        checkImage4.setOnClickListener {
            val intent = Intent(this, Join2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        checkImage5.setOnClickListener {
            val intent = Intent(this, Join2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }


    findImage.setOnClickListener {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
    }

        nextBtn.setOnClickListener {
            val intent = Intent(this, Join3Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }



        firstCheckBtn.setOnCheckedChangeListener { _, b ->
            if (b)  checkText1.setTextColor(Color.parseColor("#202020"))
            else checkText1.setTextColor(Color.parseColor("#AEAEAE"))
            if (firstCheckBtn.isChecked && secondCheckBtn.isChecked && thirdCheckBtn.isChecked && fourthCheckBtn.isChecked) {
                nextBtn.isEnabled = true
                nextBtn.setBackgroundResource(R.drawable.background_radius_maincolor)
            } else if (firstCheckBtn.isChecked || secondCheckBtn.isChecked || thirdCheckBtn.isChecked || fourthCheckBtn.isChecked || fifthCheckBtn.isChecked) {
                    nextBtn.isEnabled = false
                    nextBtn.setBackgroundResource(R.drawable.background_radius_gray)
                }

        }

        secondCheckBtn.setOnCheckedChangeListener { _, b ->
            if (b)  checkText2.setTextColor(Color.parseColor("#202020"))
            else checkText2.setTextColor(Color.parseColor("#AEAEAE"))
            if (firstCheckBtn.isChecked && secondCheckBtn.isChecked && thirdCheckBtn.isChecked && fourthCheckBtn.isChecked) {
                nextBtn.isEnabled = true
                nextBtn.setBackgroundResource(R.drawable.background_radius_maincolor)
            }  else if (firstCheckBtn.isChecked || secondCheckBtn.isChecked || thirdCheckBtn.isChecked || fourthCheckBtn.isChecked || fifthCheckBtn.isChecked){
                nextBtn.isEnabled = false
                nextBtn.setBackgroundResource(R.drawable.background_radius_gray)
            }
        }

        thirdCheckBtn.setOnCheckedChangeListener { _, b ->
            if (b)  checkText3.setTextColor(Color.parseColor("#202020"))
            else checkText3.setTextColor(Color.parseColor("#AEAEAE"))
            if (firstCheckBtn.isChecked && secondCheckBtn.isChecked && thirdCheckBtn.isChecked && fourthCheckBtn.isChecked) {
                nextBtn.isEnabled = true
                nextBtn.setBackgroundResource(R.drawable.background_radius_maincolor)
            }  else if (firstCheckBtn.isChecked || secondCheckBtn.isChecked || thirdCheckBtn.isChecked || fourthCheckBtn.isChecked || fifthCheckBtn.isChecked){
                nextBtn.isEnabled = false
                nextBtn.setBackgroundResource(R.drawable.background_radius_gray)
            }
        }

        fourthCheckBtn.setOnCheckedChangeListener { _, b ->
            if (b)  checkText4.setTextColor(Color.parseColor("#202020"))
            else checkText4.setTextColor(Color.parseColor("#AEAEAE"))
            if (firstCheckBtn.isChecked && secondCheckBtn.isChecked && thirdCheckBtn.isChecked && fourthCheckBtn.isChecked) {
                nextBtn.isEnabled = true
                nextBtn.setBackgroundResource(R.drawable.background_radius_maincolor)
            }  else if (firstCheckBtn.isChecked || secondCheckBtn.isChecked || thirdCheckBtn.isChecked || fourthCheckBtn.isChecked || fifthCheckBtn.isChecked){
                nextBtn.isEnabled = false
                nextBtn.setBackgroundResource(R.drawable.background_radius_gray)
            }
        }

        fifthCheckBtn.setOnCheckedChangeListener { _, b ->
            if (b)  {checkText5.setTextColor(Color.parseColor("#202020"))
                     checkText5_1.setTextColor(Color.parseColor("#202020")) }
            else { checkText5.setTextColor(Color.parseColor("#AEAEAE"))
                   checkText5_1.setTextColor(Color.parseColor("#AEAEAE"))}
            if (firstCheckBtn.isChecked && secondCheckBtn.isChecked && thirdCheckBtn.isChecked && fourthCheckBtn.isChecked) {
                nextBtn.isEnabled = true
                nextBtn.setBackgroundResource(R.drawable.background_radius_maincolor)
            }  else if (firstCheckBtn.isChecked || secondCheckBtn.isChecked || thirdCheckBtn.isChecked || fourthCheckBtn.isChecked || fifthCheckBtn.isChecked){
                nextBtn.isEnabled = false
                nextBtn.setBackgroundResource(R.drawable.background_radius_gray)
            }
        }




    }

    private fun onCheckChanged(compoundButton: CompoundButton) {
        when(compoundButton.id) {
            R.id.allCheckBtn -> {
                if (allCheckBtn.isChecked) {
                    firstCheckBtn.isChecked = true
                    secondCheckBtn.isChecked = true
                    thirdCheckBtn.isChecked = true
                    fourthCheckBtn.isChecked = true
                    fifthCheckBtn.isChecked = true
                    checkText1.setTextColor(Color.parseColor("#202020"))
                    checkText2.setTextColor(Color.parseColor("#202020"))
                    checkText3.setTextColor(Color.parseColor("#202020"))
                    checkText4.setTextColor(Color.parseColor("#202020"))
                    checkText5.setTextColor(Color.parseColor("#202020"))
                    checkText5_1.setTextColor(Color.parseColor("#202020"))
                    nextBtn.isEnabled = true
                    nextBtn.setBackgroundResource(R.drawable.background_radius_maincolor)
                }else {
                    firstCheckBtn.isChecked = false
                    secondCheckBtn.isChecked = false
                    thirdCheckBtn.isChecked = false
                    fourthCheckBtn.isChecked = false
                    fifthCheckBtn.isChecked = false
                    checkText1.setTextColor(Color.parseColor("#AEAEAE"))
                    checkText2.setTextColor(Color.parseColor("#AEAEAE"))
                    checkText3.setTextColor(Color.parseColor("#AEAEAE"))
                    checkText4.setTextColor(Color.parseColor("#AEAEAE"))
                    checkText5.setTextColor(Color.parseColor("#AEAEAE"))
                    checkText5_1.setTextColor(Color.parseColor("#AEAEAE"))
                    nextBtn.isEnabled = false
                    nextBtn.setBackgroundResource(R.drawable.background_radius_gray)

                }
            }
            else -> {
                allCheckBtn.isChecked = (
                        firstCheckBtn.isChecked
                                && secondCheckBtn.isChecked
                                && thirdCheckBtn.isChecked
                                && fourthCheckBtn.isChecked
                                && fifthCheckBtn.isChecked)
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
    }
}