package com.mandeum.dessert39.Join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_join3.*
import kotlinx.android.synthetic.main.activity_join3.passwordArea
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import java.util.regex.Pattern

class Join3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join3)

        idArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        passwordArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(passwordArea.length() or  passwordArea2.length() == 0) {
                    check_result2.setText("비밀번호를 입력해주세요.")
                }
           else if(!Pattern.matches("^[a-zA-Z0-9]*\$", passwordArea.toString())) {
                check_result2.setText("영문+숫자 조합 8자리 이상 입력해주세요.")
            } else if(!Pattern.matches("^\"^(?=.*\\\\d)(?=.*[~`!@#\$%\\\\^&*()-])(?=.*[a-zA-Z]).{8,20}\$\"\n", passwordArea.toString())) {
                    check_result2.setText("잘못된 비밀번호 형식입니다. 다시 입력해주세요")
                }
            }

        })

        passwordArea2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
         if (passwordArea.length() or  passwordArea2.length() == 0) {
                check_result3.setText("비밀번호를 입력해주세요.")
            }
               else if(passwordArea.getText().toString() != (passwordArea2.getText().toString())) {
                    check_result3.setText("비밀번호가 일치하지 않습니다.")
                } else {
             check_result3.setText("비밀번호가 일치합니다.")
         }
            }

        })


    }

}