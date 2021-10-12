package com.mandeum.dessert39.Join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.lifecycle.ViewModelProvider
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notification.*

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

        button3.setOnClickListener {
            val intent = Intent(this, Join3Activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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
                }else {
                    firstCheckBtn.isChecked = false
                    secondCheckBtn.isChecked = false
                    thirdCheckBtn.isChecked = false
                    fourthCheckBtn.isChecked = false
                    fifthCheckBtn.isChecked = false
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
}