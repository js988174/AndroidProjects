package com.mandeum.dessert39.Login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.user.UserApiClient
import com.mandeum.dessert39.Join.JoinActivity
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Intro.MainActivity.Companion.TAG
import com.mandeum.dessert39.R
import com.mandeum.dessert39.Sms.FindActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.joinBtn
import kotlinx.android.synthetic.main.activity_main.loginBtn

class LoginActivity : AppCompatActivity() {

    lateinit var alertDialog : AlertDialog
    lateinit var builder : AlertDialog.Builder
    lateinit var mOAuthLoginInstance : OAuthLogin
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            passwordArea.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    loginBtn.isEnabled = passwordArea.length() and emailArea.length() >= 7

                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
        loginBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        joinBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

         mContext = this

        naverBtn.setOnClickListener {
            //네이버 로그인
            mOAuthLoginInstance = OAuthLogin.getInstance()
            mOAuthLoginInstance.init(
                mContext, getString(R.string.naver_client_id)
                ,getString(R.string.naver_client_secret)
                ,getString(R.string.naver_client_name))
            mOAuthLoginInstance.startOauthLoginActivity(this, mOAthLoginHandler);

            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


        // 카카오 로그인
//                UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
//                    if (error != null) {
//                        Toast.makeText(this, "토근 정보 보기 실패", Toast.LENGTH_SHORT).show()
//                        } else if(tokenInfo != null) {
//                        Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, HomeActivity::class.java)
//                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                        }
//                    }
//
//        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//            if (error != null) {
//                Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
//            }
//            else if (token != null) {
//                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
//            }
//        }

        IdBtn.setOnClickListener {
            val intent = Intent(this, FindActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        kakaoBtn.setOnClickListener() {
            kakaoLogin()
        }
    }


    // 네이버 로그인
  private  val mOAthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {
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

    // 로그인 팝업창
     fun showdialog() {
        try{
            var str_tittle = "정보 틀림"
            var str_message = "로그인정보가 일치하지 않습니다.\n아이디나 비밀번호를 확인 후 다시 입력해주세요."

            builder = AlertDialog.Builder(this)
            builder.setTitle(str_tittle) //팝업창 타이틀 지정
            builder.setIcon(R.drawable.ic_pager_item_1) // 팝업창 이미지
            builder.setMessage(str_message) //팝업창 내용 지정

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

}
