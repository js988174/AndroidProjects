package com.mandeum.dessert39.Join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.mandeum.dessert39.Find.Id.Find1Activity
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_find1.*
import kotlinx.android.synthetic.main.activity_join3.*
import java.util.regex.Pattern


class Join3Activity : AppCompatActivity() {

    private var passwordValidation = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,15}.\$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join3)

//        joinBackGround()
        joinArea()

        successBtn.setOnClickListener {
            val intent = Intent(this, Join5Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        joinIdArea.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                join_id_layout.setBackgroundResource(R.drawable.login_default)
            } else {
                if (joinIdArea.length() >= 1) {
                    join_id_layout.setBackgroundResource(R.drawable.login_default)
                } else if (joinIdArea.length() == 0) {
                    join_id_layout.setBackgroundResource(R.drawable.login_black)
                }
            }
        }

        joinPwArea.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                join_pw_layout.setBackgroundResource(R.drawable.login_default)
            } else {
                if (joinPwArea.length() >= 1) {
                    join_pw_layout.setBackgroundResource(R.drawable.login_default)
                } else if (joinPwArea.length() == 0) {
                    join_pw_layout.setBackgroundResource(R.drawable.login_black)
                }
            }
        }

        joinPw2Area.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                join_pw2_layout.setBackgroundResource(R.drawable.login_default)
            } else {
                if (joinPw2Area.length() >= 1) {
                    join_pw2_layout.setBackgroundResource(R.drawable.login_default)
                } else if (joinPw2Area.length() == 0) {
                    join_pw2_layout.setBackgroundResource(R.drawable.login_black)
                }
            }
        }

        joinEmailArea.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                join_email_layout.setBackgroundResource(R.drawable.login_default)
            } else {
                if (joinEmailArea.length() >= 1) {
                    join_email_layout.setBackgroundResource(R.drawable.login_default)
                } else if (joinEmailArea.length() == 0) {
                    join_email_layout.setBackgroundResource(R.drawable.login_black)
                }
            }
        }

        joinNicknameArea.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                join_nickname_layout.setBackgroundResource(R.drawable.login_default)
            } else {
                if (joinNicknameArea.length() >= 1) {
                    join_nickname_layout.setBackgroundResource(R.drawable.login_default)
                } else if (joinNicknameArea.length() == 0) {
                    join_nickname_layout.setBackgroundResource(R.drawable.login_black)
                }
            }
        }


        clearId2.setOnClickListener {
            joinIdArea.text.clear()
        }

        clearPw2.setOnClickListener {
            joinPwArea.text.clear()
        }

        clear2Pw2.setOnClickListener {
            joinPw2Area.text.clear()
        }

        findImage2.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

    }

    private fun joinArea() {
        joinIdArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! > 0) {
                    checkBtnId.setBackgroundResource(R.drawable.background_radius_black_button)
                } else if (p0.length!! < 1) {
                    checkBtnId.setBackgroundResource(R.drawable.background_radius_gray2)
                }

                if (p0?.length == 0) {
                    clearId.isVisible = true
                    clearId2.isVisible = false
                    join_id_layout.setBackgroundResource(R.drawable.login_black)
                } else if (p0?.length!! > 0) {
                    clearId.isVisible = false
                    clearId2.isVisible = true
                    join_id_layout.setBackgroundResource(R.drawable.login_default)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                checkBtnId.setOnClickListener {
                    idText3.text = "사용가능한 아이디입니다."
                    idText3.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black2))
                }
            }

        })


        joinPwArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 0) {
                    clearPw.isVisible = true
                    clearPw2.isVisible = false
                    join_pw_layout.setBackgroundResource(R.drawable.login_black)
                } else if (p0?.length!! > 0) {
                    clearPw.isVisible = false
                    clearPw2.isVisible = true
                    join_pw_layout.setBackgroundResource(R.drawable.login_default)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,15}$", joinPwArea.toString())) {
                    pwText3.text = "영문+숫자 조합 8자 이상"
                    pwText3.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black2))
                } else {
                    pwText3.text = "올바른 식을 입력해주세요."
                    pwText3.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black2))
                }
            }

        })


        joinPw2Area.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 0) {
                    clear2Pw.isVisible = true
                    clear2Pw2.isVisible = false
                    join_pw2_layout.setBackgroundResource(R.drawable.login_black)
                } else if (p0?.length!! > 0) {
                    clear2Pw.isVisible = false
                    clear2Pw2.isVisible = true
                    join_pw2_layout.setBackgroundResource(R.drawable.login_default)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (joinPwArea.text.toString() == joinPw2Area.text.toString()) {
                    pw2Text3.text = "일치합니다."
                    pw2Text3.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black2))
                } else {
                    pw2Text3.text = "비밀번호가 일치하지 않습니다."
                    pw2Text3.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black2))
                }
            }

        })


        joinEmailArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 0) {
                    join_email_layout.setBackgroundResource(R.drawable.login_black)
                } else if (p0?.length!! > 0) {
                    join_email_layout.setBackgroundResource(R.drawable.login_default)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        joinNicknameArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 0) {
                    join_nickname_layout.setBackgroundResource(R.drawable.login_black)
                } else if (p0?.length!! > 0) {
                    join_nickname_layout.setBackgroundResource(R.drawable.login_default)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })



    }

//    private fun joinBackGround() {
//        joinIdArea.onFocusChangeListener = View.OnFocusChangeListener {
//                _, p1 ->
//            if (p1) {
//                join_id_layout.setBackgroundResource(R.drawable.login_default)
//            } else if (joinIdArea.length() == 0) {
//                join_id_layout.setBackgroundResource(R.drawable.login_black)
//            } else if (joinIdArea.length() > 0) {
//                join_id_layout.setBackgroundResource(R.drawable.login_default)
//            }
//        }
//
//        joinPwArea.onFocusChangeListener = View.OnFocusChangeListener {
//                _, p1 ->
//            if (p1) {
//                join_pw_layout.setBackgroundResource(R.drawable.login_default)
//            } else if (joinIdArea.length() == 0) {
//                join_pw_layout.setBackgroundResource(R.drawable.login_black)
//            } else if (joinPwArea.length() > 0){
//                join_pw_layout.setBackgroundResource(R.drawable.login_default)
//            }
//        }
//
//        joinPw2Area.onFocusChangeListener = View.OnFocusChangeListener {
//                _, p1 ->
//            if (p1) {
//                join_pw2_layout.setBackgroundResource(R.drawable.login_default)
//            } else if (joinIdArea.length() == 0) {
//                join_pw2_layout.setBackgroundResource(R.drawable.login_black)
//            } else if (joinPw2Area.length() > 0){
//                join_pw2_layout.setBackgroundResource(R.drawable.login_default)
//            }
//        }
//
//        joinEmailArea.onFocusChangeListener = View.OnFocusChangeListener {
//                _, p1 ->
//            if (p1) {
//                join_email_layout.setBackgroundResource(R.drawable.login_default)
//            } else if (joinIdArea.length() == 0) {
//                join_email_layout.setBackgroundResource(R.drawable.login_black)
//            } else if (joinIdArea.length() > 0) {
//                join_email_layout.setBackgroundResource(R.drawable.login_default)
//            }
//        }
//
//        joinNicknameArea.onFocusChangeListener = View.OnFocusChangeListener {
//                _, p1 ->
//            if (p1) {
//                join_nickname_layout.setBackgroundResource(R.drawable.login_default)
//            } else if (joinIdArea.length() == 0) {
//                join_nickname_layout.setBackgroundResource(R.drawable.login_black)
//            } else if (joinIdArea.length() > 0) {
//                join_nickname_layout.setBackgroundResource(R.drawable.login_default)
//            }
//        }
//    }


    override fun onBackPressed() {
        val intent = Intent(this, JoinActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
    }

}