package com.mandeum.dessert39.Login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.mandeum.dessert39.Find.Id.Find1Activity
import com.mandeum.dessert39.Find.Password.FindPw1Activity
import com.mandeum.dessert39.Join.JoinActivity
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Intro.MainActivity.Companion.TAG
import com.mandeum.dessert39.Login.ServerApi.LoginApi
import com.mandeum.dessert39.Login.ServerApi.LoginApi.Companion.login
import com.mandeum.dessert39.Login.ServerApi.LoginModel
import com.mandeum.dessert39.R
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_find2.*
import kotlinx.android.synthetic.main.activity_find_pw2.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.loginBtn
import java.util.*
import kotlin.concurrent.thread


class LoginActivity : AppCompatActivity() {


    lateinit var alertDialog : AlertDialog
    lateinit var builder : AlertDialog.Builder
    lateinit var mOAuthLoginInstance : OAuthLogin
    lateinit var mContext: Context
    private var mIsShowPass = false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager

        layout.setOnClickListener {
            imm.hideSoftInputFromWindow(idArea2.windowToken, 0)
            imm.hideSoftInputFromWindow(passwordArea2.windowToken, 0)
        }



//        // SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
//        if(MySharedPreferences.getUserId(this).isNullOrBlank()
//            || MySharedPreferences.getUserPass(this).isNullOrBlank()) {
//            Login()
//        }
//        else {
//            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }


            backGround()
            Area()

            clear.setOnClickListener {
                idArea2.text.clear()
            }

            watch_color.setOnClickListener {
                mIsShowPass = !mIsShowPass
                showPassword(mIsShowPass)
            }

            showPassword(mIsShowPass)


        loginBtn.setOnClickListener {
            loginApi(idArea2 , passwordArea2, idArea2.text.toString(), passwordArea2.text.toString())

        }





        findId.setOnClickListener {
            val intent = Intent(this, Find1Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        findPw.setOnClickListener {
            val intent = Intent(this, FindPw1Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        joinBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }



         mContext = this

        naverBtn.setOnClickListener {
            //네이버 로그인
            mOAuthLoginInstance = OAuthLogin.getInstance()
            mOAuthLoginInstance.init(
                mContext, getString(R.string.naver_client_id)
                ,getString(R.string.naver_client_secret)
                ,getString(R.string.naver_client_name))
            mOAuthLoginInstance.startOauthLoginActivity(this, mOAthLoginHandler)

            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


//         카카오 로그인
                UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                    if (error != null) {
                        Toast.makeText(this, "토근 정보 보기 실패", Toast.LENGTH_SHORT).show()
                        } else if(tokenInfo != null) {
                        Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        }
                    }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }


        kakaoBtn.setOnClickListener() {
            kakaoLogin()
        }
    }


    // 네이버 로그인
  private  val mOAthLoginHandler: OAuthLoginHandler = @SuppressLint("HandlerLeak")
    object : OAuthLoginHandler() {
        override fun run(@SuppressLint("HandlerLeak") success: Boolean) {
            if (success) {
                val accessToken: String = mOAuthLoginInstance.getAccessToken(mContext)
                val refreshToken: String = mOAuthLoginInstance.getRefreshToken(mContext)
                val expiresAt: Long = mOAuthLoginInstance.getExpiresAt(mContext)
                val tokenType: String = mOAuthLoginInstance.getTokenType(mContext)
            } else {
                val errorCode : String = mOAuthLoginInstance.getLastErrorCode(mContext).code
                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)

                Toast.makeText(
                    baseContext, "errorCode" + errorCode
                            + ", errorDe" + errorDesc, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // 카카오 계정 로그인
    private fun kakaoLogin() {
        UserApiClient.instance.loginWithKakaoAccount(mContext) { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
            }
        }
    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            passwordArea2.transformationMethod = HideReturnsTransformationMethod.getInstance()
            watch_color.setImageResource(R.drawable.watch_color2)
        } else {
            passwordArea2.transformationMethod = PasswordTransformationMethod.getInstance()
            watch_color.setImageResource(R.drawable.watch_color1)
        }
        passwordArea2.setSelection(passwordArea2.text.toString().length)
    }

    private fun Area() {
        idArea2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 0) {
                    clear.isVisible = false
                    id_layout.setBackgroundResource(R.drawable.login_black)
                } else if (p0?.length!! > 0) {
                    clear.isVisible = true
                    id_layout.setBackgroundResource(R.drawable.login_default)
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }


        })


        passwordArea2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (idArea2.length() > 0 && passwordArea2.length() > 0) {
                    loginBtn.setBackgroundResource(R.drawable.background_radius_maincolor)
                } else if (idArea2.length() < 1 || passwordArea2.length() < 1) {
                    loginBtn.setBackgroundResource(R.drawable.background_radius_gray)
                }
                if (p0?.length == 0) {
                    watch_default.isVisible = true
                    watch_color.isVisible = false
                    pw_layout.setBackgroundResource(R.drawable.login_black)
                } else if (p0?.length!! > 0) {
                    watch_color.isVisible = true
                    watch_default.isVisible = false
                    pw_layout.setBackgroundResource(R.drawable.login_default)
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun loginApi(idArea2 : EditText, passwordArea2 : EditText, userId : String, userPw : String) {

        if (idArea2.length() == 0 && passwordArea2.length() == 0) {
            Toast.makeText(this, "아이디와 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
        } else if (idArea2.length() == 0) {
            Toast.makeText(this, "아이디를 입력해 주세요.", Toast.LENGTH_SHORT).show()
        } else if (passwordArea2.length() == 0) {
            Toast.makeText(this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
        } else if (idArea2.length() > 0 && passwordArea2.length() > 0) {
            val uuid = android.provider.Settings.Secure.getString(this.contentResolver,
                Settings.Secure.ANDROID_ID)
            val byteArray: ByteArray = uuid.toByteArray()
            val uuidByte = UUID.nameUUIDFromBytes(byteArray).toString()
            Log.d("test", uuidByte)


            thread(start = true) {
                val userInfo: LoginModel = LoginApi.login(userId, userPw, uuidByte)
                val loginModel = LoginModel(userInfo.errCode,
                    userInfo.strToken, userInfo.isFirstLogin)


                if (loginModel.errCode == "0000") {
//                    com.mandeum.dessert39.SharedPreferences.Application.mySharedPreferences.setToken(
//                        this,
//                        userInfo.strToken)
//                    com.mandeum.dessert39.SharedPreferences.Application.mySharedPreferences.setToken(
//                        this,
//                        userInfo.isFirstLogin)
//                    com.mandeum.dessert39.SharedPreferences.Application.mySharedPreferences.setToken(
//                        this,
//                        uuidByte)

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)

                } else if (loginModel.errCode == "0003") {
                    this.runOnUiThread {
                        Toast.makeText(this, "회원정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else if (loginModel.errCode == "0004") {
                    this.runOnUiThread {
                        Toast.makeText(this, "회원 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            }
        }

   private fun backGround() {
        idArea2.onFocusChangeListener = View.OnFocusChangeListener { _, p1 ->
            if (p1) {
                id_layout.setBackgroundResource(R.drawable.login_default)
            } else if (idArea2.length() == 0) {
                id_layout.setBackgroundResource(R.drawable.login_black)
            }
        }

        passwordArea2.onFocusChangeListener = View.OnFocusChangeListener {
                _, p1 ->
            if (p1) {
                pw_layout.setBackgroundResource(R.drawable.login_default)
            } else if (idArea2.length() == 0) {
                    pw_layout.setBackgroundResource(R.drawable.login_black)
                }
            }


    }

    // 로그인 팝업창
     fun showdialog() {
        try {
            var str_tittle = "정보 틀림"
            var str_message = "로그인정보가 일치하지 않습니다.\n아이디나 비밀번호를 확인 후 다시 입력해주세요."

            builder = AlertDialog.Builder(this)
            builder.setTitle(str_tittle) //팝업창 타이틀 지정
            builder.setMessage(str_message) //팝업창 내용 지정

            alertDialog = builder.create()
            try {
                alertDialog.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




//   fun Login() {
//
//       loginBtn.setOnClickListener {
//
//           if(idArea2.text.isNullOrBlank() || passwordArea2.text.isNullOrBlank()) {
//               Toast.makeText(this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
//           } else {
//               MySharedPreferences.setUserId(this, idArea2.text.toString())
//               MySharedPreferences.setUserPass(this, passwordArea2.text.toString())
//               Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
//               var intent = Intent(this, HomeActivity::class.java)
//               startActivity(intent)
//               finish()
//           }
//       }
//   }

}
