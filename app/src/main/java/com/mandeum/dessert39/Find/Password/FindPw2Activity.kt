package com.mandeum.dessert39.Find.Password

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_find1.*
import kotlinx.android.synthetic.main.activity_find_pw2.*
import kotlinx.android.synthetic.main.activity_login.*

class FindPw2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pw2)

        findPw2Area()
        FindPwBackGround()

        clear4.setOnClickListener {
            pw_reArea.text.clear()
        }
        clear5.setOnClickListener {
            pw_reArea2.text.clear()
        }
        clear6.setOnClickListener {
            pw_reArea2.text.clear()
        }

        findBtn.setOnClickListener {

            if (pw_reArea.length() == 0 && pw_reArea2.length() == 0) {
                Toast.makeText(this, "둘 다 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (pw_reArea.length() == 0) {
                Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (pw_reArea2.length() == 0) {
                Toast.makeText(this, "비밀번호 확인을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (pw_reArea.text.toString() != pw_reArea2.text.toString()) {
                Toast.makeText(this, "비밀번호 확인을 제대로 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (pw_reArea.text.toString() == pw_reArea2.text.toString()) {
                val intent = Intent(this, FindPw3Activity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            }

        }

    }

    private fun findPw2Area() {

        pw_reArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! > 0) {
                    clear7.isVisible = false
                    clear4.isVisible = true
                    pw_re_layout.setBackgroundResource(R.drawable.login_default)
                } else if (p0?.length == 0) {
                    clear7.isVisible = true
                    clear4.isVisible = false
                    pw_re_layout.setBackgroundResource(R.drawable.login_black)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        pw_reArea2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! > 0) {
                    clear5.isVisible = false
                    clear6.isVisible = true
                    pw_re_layout2.setBackgroundResource(R.drawable.login_default)
                } else if (p0?.length == 0) {
                    clear5.isVisible = true
                    clear6.isVisible = false
                    pw_re_layout2.setBackgroundResource(R.drawable.login_black)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }

    private fun FindPwBackGround() {
        pw_reArea.onFocusChangeListener = View.OnFocusChangeListener {
                _, p1 ->
            if (p1) {
                pw_re_layout.setBackgroundResource(R.drawable.login_default)
            } else if (pw_reArea.length() == 0) {
                pw_re_layout.setBackgroundResource(R.drawable.login_black)
            }
        }

        pw_reArea2.onFocusChangeListener = View.OnFocusChangeListener {
                _, p1 ->
            if (p1) {
                pw_re_layout2.setBackgroundResource(R.drawable.login_default)
            } else if (pw_reArea2.length() == 0) {
                pw_re_layout2.setBackgroundResource(R.drawable.login_black)
            }
        }

    }
}