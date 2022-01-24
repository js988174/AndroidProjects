package com.mandeum.dessert39.Find.Id

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.Login.ServerApi.Model.Login.FindIdModel
import com.mandeum.dessert39.Login.ServerApi.Model.Login.SmsModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_find1.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


class Find1Activity : AppCompatActivity() {

    private var tvTimer: TextView? = null
    lateinit var alertDialog : android.app.AlertDialog
    lateinit var builder : android.app.AlertDialog.Builder

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find1)

        findArea()
        Find1backGround()


        tvTimer = findViewById(R.id.counter)

        clear1.setOnClickListener {
            nameArea.text.clear()
        }

        clear2.setOnClickListener {
            phoneArea1.text.clear()
        }

        clear3.setOnClickListener {
            phoneArea1.text.clear()
        }

        checkBtn.setOnClickListener {
            findIdApi(nameArea , phoneArea1, nameArea.text.toString(), phoneArea1.text.toString())
            smsApi(nameArea, phoneArea1, nameArea.text.toString(), phoneArea1.text.toString())
        }

        checkBtn2.setOnClickListener {
            if (phoneArea2.length() == 5) {
                val intent = Intent(this, Find2Activity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            }
        }
        findImage.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent. FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

    }


    private fun RequestAlter() {
        try{
            var str_message = "인증번호를 발송하였습니다"


            builder = android.app.AlertDialog.Builder(this)
            builder.setMessage(str_message) //팝업창 내용 지정
            builder.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

            })
            // builder.setCancelable(false) //외부 레이아웃 클릭시도 팝업창이 사라지지않게 설정
            alertDialog = builder.create()
            try {
                alertDialog.show()
            }
            catch (e : Exception){
                e.printStackTrace()
            }
        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }

    private fun findIdApi(nameArea: EditText, phoneArea1 : EditText, NAME : String, PHONE: String) {
        checkBtn2.isEnabled = true
        if (phoneArea1.length() == 0 && nameArea.length() == 0) {
            Toast.makeText(this, "이름, 핸드폰 번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            phone_layout2.isVisible = false
        } else if (nameArea.length() == 0) {
            Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            phone_layout2.isVisible = false
        } else if (phoneArea1.length() == 0) {
            Toast.makeText(this, "핸드폰 번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            phone_layout2.isVisible = false
        } else if (nameArea.length() >= 1 && phoneArea1.length() >= 1) {


        }


        thread(start = true) {
            val userInfo : FindIdModel = ServerApi.findId(NAME, PHONE)
            val findModel = FindIdModel(userInfo.errCode)

            if (findModel.errCode == "0000") {
                this.runOnUiThread {
                    checkBtn.setBackgroundResource(R.drawable.background_radius_black_button)
                    checkBtn.isEnabled = true
                    Toast.makeText(this, "회원정보 찾기 성공.", Toast.LENGTH_SHORT).show()
                    phone_layout2.isVisible = true
                    RequestAlter()
                    val countDown = object : CountDownTimer(10000 * 3, 1000) {
                        override fun onTick(p0: Long) {
                            // countDownInterval 마다 호출 (여기선 1000ms)
                            tvTimer?.text = getString(
                                R.string.formatted_time,
                                TimeUnit.MILLISECONDS.toMinutes(p0) % 60,
                                TimeUnit.MILLISECONDS.toSeconds(p0) % 60
                            )
                        }

                        override fun onFinish() {
                            // 타이머가 종료되면 호출
                            timerAlter()
                            checkBtn2.isEnabled = false
                            checkBtn2.setBackgroundResource(R.drawable.background_radius_gray2)
                        }
                    }.start()
                }

            } else if (findModel.errCode == "0003") {
                this.runOnUiThread {
                    phone_layout2.isVisible = false
                    Toast.makeText(this, "회원정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


    private fun timerAlter() {
        try{
            var str_tittle = "휴대폰 인증"
            var str_message = "인증시간이 초과되었습니다."


            builder = android.app.AlertDialog.Builder(this
            )
            builder.setTitle(str_tittle) //팝업창 타이틀 지정
            builder.setMessage(str_message) //팝업창 내용 지정
            builder.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

            })
            // builder.setCancelable(false) //외부 레이아웃 클릭시도 팝업창이 사라지지않게 설정
            alertDialog = builder.create()
            try {
                alertDialog.show()
            }
            catch (e : Exception){
                e.printStackTrace()
            }
        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }

    private fun findArea() {
        nameArea.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               if (p0?.length == 0) {
                   clear1.isVisible = false
                   name_layout.setBackgroundResource(R.drawable.login_black)
               } else if (p0?.length!! > 0) {
                   clear1.isVisible = true
                   name_layout.setBackgroundResource(R.drawable.login_default)
               } else if (p0?.length > 0 && phoneArea1.length() > 0) {
                    checkBtn.setBackgroundResource(R.drawable.background_radius_black_button)
               } else {
                   checkBtn.setBackgroundResource(R.drawable.background_radius_gray2)
               }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        phoneArea1.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0?.length!! > 0) {
                        checkBtn.setBackgroundResource(R.drawable.background_radius_black_button)
                    } else if (p0.length!! < 1) {
                        checkBtn.setBackgroundResource(R.drawable.background_radius_gray2)
                    } else if (nameArea.length() > 0 && p0?.length > 0) {
                        checkBtn.setBackgroundResource(R.drawable.background_radius_black_button)
                    } else {
                        checkBtn.setBackgroundResource(R.drawable.background_radius_gray2)
                    }
                if (p0?.length!! > 0) {
                        clear2.isVisible = false
                        clear3.isVisible = true
                    phone_layout.setBackgroundResource(R.drawable.login_default)
                } else if (p0?.length == 0) {
                    clear2.isVisible = true
                    clear3.isVisible = false
                    phone_layout.setBackgroundResource(R.drawable.login_black)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        phoneArea2.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! == 5) {
                    checkBtn2.setBackgroundResource(R.drawable.background_radius_black_button)
                } else if (p0.length!! < 5) {
                    checkBtn2.setBackgroundResource(R.drawable.background_radius_gray2)
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

    private fun Find1backGround() {
        nameArea.onFocusChangeListener = View.OnFocusChangeListener {
                _, p1 ->
            if (p1) {
                name_layout.setBackgroundResource(R.drawable.login_default)
            } else if (nameArea.length() == 0) {
                name_layout.setBackgroundResource(R.drawable.login_black)
            }
        }

        phoneArea1.onFocusChangeListener = View.OnFocusChangeListener {
                _, p1 ->
            if (p1) {
                phone_layout.setBackgroundResource(R.drawable.login_default)
            } else if (phoneArea1.length() == 0) {
                phone_layout.setBackgroundResource(R.drawable.login_black)
            }
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
    }


    private fun smsApi(nameArea: EditText, phoneArea1: EditText, HPNUM:String, CONTENT:String) {
        thread(start = true) {
            val userInfo: SmsModel = ServerApi.sendSms(HPNUM, CONTENT)
            val smsModel = SmsModel(
                userInfo.errCode,
                userInfo.requestId,
                userInfo.requestTime,
                userInfo.statusCode
            )

            if (smsModel.errCode == "0000") {
                this.runOnUiThread {
                    Toast.makeText(this, "메세지 보내기 성공.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}