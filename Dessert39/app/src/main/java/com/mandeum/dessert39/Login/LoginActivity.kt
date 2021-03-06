package com.mandeum.dessert39.Login

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
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
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.mandeum.dessert39.Find.Id.Find1Activity
import com.mandeum.dessert39.Find.Password.FindPw1Activity
import com.mandeum.dessert39.Join.JoinActivity
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Login.ServerApi.Model.Login.LoginModel
import com.mandeum.dessert39.Login.ServerApi.Model.Login.SnsLoginModel
import com.mandeum.dessert39.R
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.loginBtn
import java.util.*
import kotlin.concurrent.thread
import org.json.JSONException
import org.json.JSONObject
import com.nhn.android.naverlogin.OAuthLogin.mOAuthLoginHandler


class LoginActivity : AppCompatActivity() {


    lateinit var alertDialog: AlertDialog
    lateinit var builder: AlertDialog.Builder
     var mOAuthLoginInstance: OAuthLogin? = null
    lateinit var mContext: Context
    private var mIsShowPass = false
    var firebaseToken: String = ""
//     var prefs: SharedPreferences = getSharedPreferences("prefs_name", Context.MODE_PRIVATE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var keyHash = Utility.getKeyHash(this)
        Log.d("keyHash", keyHash.toString())

        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseToken = it.result.toString()
                val shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                val editor = shared.edit()
                editor.putString("FirebaseToken", firebaseToken)
                Log.d("firebase", firebaseToken)
            } else {
                Toast.makeText(
                    this,
                    "?????? ????????? ???????????? ????????????. ????????? ????????????.\nFirebaseTokenIsSuccessfulFalse",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        val imm =
            getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager

        layout.setOnClickListener {
            imm.hideSoftInputFromWindow(idArea2.windowToken, 0)
            imm.hideSoftInputFromWindow(passwordArea2.windowToken, 0)
        }


//        // SharedPreferences ?????? ?????? ???????????? ?????? ?????? ??? -> Login
//        if(MySharedPreferences.getUserId(this).isNullOrBlank()
//            || MySharedPreferences.getUserPass(this).isNullOrBlank()) {
//            Login()
//        }
//        else {
//            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}??? ?????? ????????? ???????????????.", Toast.LENGTH_SHORT).show()
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
            loginApi(idArea2, passwordArea2, idArea2.text.toString(), passwordArea2.text.toString(),firebaseToken)

        }

        // ?????? ??????
        mOAuthLoginInstance?.startOauthLoginActivity(mContext as Activity?, mOAuthLoginHandler)


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
            //????????? ?????????
            mOAuthLoginInstance = OAuthLogin.getInstance()
            mOAuthLoginInstance?.init(
                mContext,
                getString(R.string.naver_client_id),
                getString(R.string.naver_client_secret),
                getString(R.string.naver_client_name)
            )
            mOAuthLoginInstance?.startOauthLoginActivity(this, mOAthLoginHandler)

//            mOAthLoginHandler
        }


//         ????????? ?????????
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {

            } else if (tokenInfo != null) {

            }
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "????????? ?????? ???(?????? ??????)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "???????????? ?????? ???", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "?????? ????????? ???????????? ?????? ????????? ??? ?????? ??????", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "?????? ???????????? ??????", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "???????????? ?????? scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "????????? ???????????? ??????(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "?????? ?????? ??????", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "?????? ?????? ????????? ??????", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "?????? ??????", Toast.LENGTH_SHORT).show()
                    }
                }
//                Toast.makeText(this, "???????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
            } else if (token != null) {

                Toast.makeText(this, "???????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
                UserApiClient.instance.me { user, error ->
                    val id = user?.kakaoAccount?.email.toString()
                    val idx: Int = id!!.indexOf("@")
                    val mail: String = id.substring(0, idx)

                    val birthYear = user?.kakaoAccount?.birthyear.toString()
                    val birthDay = user?.kakaoAccount?.birthday.toString()
                    val birth = birthYear + birthDay

                    snsApi2(
                        ID = mail , EMAIL = user?.kakaoAccount?.email.toString(), NAME =user?.kakaoAccount?.legalName.toString(),
                        PHONE = user?.kakaoAccount?.phoneNumber.toString(), BIRTH = birth)

                    Log.d("??????", mail)
                    Log.d("??????", user?.kakaoAccount?.email.toString())
                    Log.d("??????", user?.kakaoAccount?.legalName.toString())
                    Log.d("??????", user?.kakaoAccount?.phoneNumber.toString())
                    Log.d("??????", birth)
                }
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()

            }
        }



        kakaoBtn.setOnClickListener() {
            UserApiClient.instance.run {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)) {

                    UserApiClient.instance.loginWithKakaoTalk(mContext, callback = callback)
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(mContext, callback = callback)
                }


            }
        }
    }


   inner class RequestApiTask(private val mContext: Context,private val mOAuthLoginInstance: OAuthLogin) :
        AsyncTask<Void?, Void?, String>() {
        override fun onPreExecute() {}

       override fun doInBackground(vararg p0: Void?): String {
           val url = "https://openapi.naver.com/v1/nid/me"
           val accessToken = mOAuthLoginInstance.getAccessToken(mContext)
           return mOAuthLoginInstance.requestApi(mContext, accessToken, url)
       }

        override fun onPostExecute(content: String) {
            try {
                val loginResult = JSONObject(content)
                if (loginResult.getString("resultcode") == "00") {
                    val response = loginResult.getJSONObject("response")
                    val email = response.optString("email","")
                    val name = response.optString("name","")
                    val mobile = response.getString("mobile")
                    val birthday = response.optString("birthday", "")
                    val birthyear = response.optString("birthyear", "")

                    var idx: Int = email.indexOf("@")
                    var mail: String = email.substring(0, idx)

                    Log.d("??????", mail)
                    Log.d("??????", email)
                    Log.d("??????", name)
                    Log.d("??????", mobile)
                    Log.d("??????", birthyear + birthday)
                    Log.d("??????", response.toString())
                    Log.d("??????", loginResult.toString())

                        snsApi(
                            ID = mail,
                            EMAIL = email,
                            NAME = name,
                            PHONE = mobile,
                            BIRTH = birthyear + birthday
                        , firebaseToken)

                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }


   }


    private fun next() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }




        private val mOAthLoginHandler: OAuthLoginHandler = @SuppressLint("HandlerLeak")
        object : OAuthLoginHandler() {
            override fun run(@SuppressLint("HandlerLeak") success: Boolean) {
                if (success) {
                    val accessToken: String = mOAuthLoginInstance!!.getAccessToken(mContext)
                    Log.d("login_token", accessToken)
                    val refreshToken: String = mOAuthLoginInstance!!.getRefreshToken(mContext)
                    Log.d("login_token", refreshToken)
                    val expiresAt: Long = mOAuthLoginInstance!!.getExpiresAt(mContext)
                    val tokenType: String = mOAuthLoginInstance!!.getTokenType(mContext)
                    RequestApiTask(mContext, mOAuthLoginInstance!!).execute()

                    next()
                } else {
                    val errorCode: String = mOAuthLoginInstance!!.getLastErrorCode(mContext).code
                    val errorDesc = mOAuthLoginInstance!!.getLastErrorDesc(mContext)

                    Toast.makeText(
                        baseContext, "errorCode" + errorCode
                                + ", errorDe" + errorDesc, Toast.LENGTH_SHORT
                    ).show()
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


    @SuppressLint("HardwareIds")
    private fun snsApi(ID: String, EMAIL: String, NAME: String, PHONE: String, BIRTH: String, firebaseToken: String) {

        val uuid = android.provider.Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        val byteArray: ByteArray = uuid.toByteArray()
        val uuidByte = java.util.UUID.nameUUIDFromBytes(byteArray).toString()

        val shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = shared.edit()

        thread(start = true) {
            val user: SnsLoginModel = ServerApi.Snslogin(ID, EMAIL, NAME, PHONE, BIRTH, firebaseToken)
            val snsLoginModel = SnsLoginModel(user.errCode, user.errMsg, user.strToken, user.isFirstLogin, user.cardImg)
            if (snsLoginModel.errCode == "0000") {
                editor.putString("LoginToken",user.strToken)
                editor.putString("FirebaseToken", firebaseToken)
                editor.putString("FirstLogin", user.isFirstLogin)

                editor.apply()

                Log.d("sdasd", user.strToken)
                Log.d("user_token", user.strToken)
                this.runOnUiThread {
                    Toast.makeText(this, "????????? ?????? ???????????? ??????", Toast.LENGTH_SHORT).show()

                }
            }  else if (snsLoginModel.errCode == "0001") {
                this.runOnUiThread {
                    Toast.makeText(this, "?????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    @SuppressLint("HardwareIds")
    private fun snsApi2(ID: String, EMAIL: String, NAME: String, PHONE: String, BIRTH: String) {

//        val editor = prefs.edit()
        val uuid = android.provider.Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        val byteArray: ByteArray = uuid.toByteArray()
        val uuidByte2 = java.util.UUID.nameUUIDFromBytes(byteArray).toString()
        val shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = shared.edit()
        thread(start = true) {
            val user2: SnsLoginModel = ServerApi.Snslogin2(ID, EMAIL, NAME, PHONE, BIRTH, uuidByte2)
            val snsLoginModel = SnsLoginModel(user2.errCode,user2.errMsg, user2.strToken, user2.isFirstLogin, user2.cardImg)
            Log.d("user_token", user2.strToken)

            if (snsLoginModel.errCode == "0000") {
                editor.putString("LoginToken",user2.strToken)
                editor.putString("AndroidUUID", uuidByte2)
                editor.putString("FirstLogin", user2.isFirstLogin)

                editor.apply()
                Log.d("user_token", user2.strToken)
                this.runOnUiThread {
                    Toast.makeText(this, "????????? ?????? ???????????? ??????", Toast.LENGTH_SHORT).show()
                }
            }  else if (snsLoginModel.errCode == "0001") {
                this.runOnUiThread {
                    Toast.makeText(this, "?????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    @SuppressLint("HardwareIds")
    private fun loginApi(idArea2 : EditText, passwordArea2 : EditText, userId : String, userPw : String, firebase: String) {
//        val editor = prefs.edit()
        if (idArea2.length() == 0 && passwordArea2.length() == 0) {
            Toast.makeText(this, "???????????? ??????????????? ????????? ?????????.", Toast.LENGTH_SHORT).show()
        } else if (idArea2.length() == 0) {
            Toast.makeText(this, "???????????? ????????? ?????????.", Toast.LENGTH_SHORT).show()
        } else if (passwordArea2.length() == 0) {
            Toast.makeText(this, "??????????????? ????????? ?????????.", Toast.LENGTH_SHORT).show()
        } else if (idArea2.length() > 0 && passwordArea2.length() > 0) {

            val shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val editor = shared.edit()


            thread(start = true) {
                val userInfo: LoginModel = ServerApi.login(userId, userPw, firebaseToken)
                val loginModel = LoginModel(userInfo.errCode, userInfo.strToken, userInfo.isFirstLogin)

                Log.d("sdasd", userInfo.strToken)
                Log.d("user_token", userInfo.strToken)

                if (loginModel.errCode == "0000") {

                    editor.putString("LoginToken",userInfo.strToken)
                    editor.putString("FirebaseToken", firebaseToken)
                    editor.putString("FirstLogin", userInfo.isFirstLogin)

                    editor.apply()

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)

                } else if (loginModel.errCode == "0003") {
                    this.runOnUiThread {
                        Toast.makeText(this, "??????????????? ?????? ??? ????????????.", Toast.LENGTH_SHORT).show()
                    }
                } else if (loginModel.errCode == "0004") {
                    this.runOnUiThread {
                        Toast.makeText(this, "?????? ??????????????? ????????????.", Toast.LENGTH_SHORT).show()
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

    // ????????? ?????????
     fun showdialog() {
        try {
            var str_tittle = "?????? ??????"
            var str_message = "?????????????????? ???????????? ????????????.\n???????????? ??????????????? ?????? ??? ?????? ??????????????????."

            builder = AlertDialog.Builder(this)
            builder.setTitle(str_tittle) //????????? ????????? ??????
            builder.setMessage(str_message) //????????? ?????? ??????

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


//    private fun logout() {
//        if (mOAuthLoginModule != null) {
//            mOAuthLoginModule!!.logout(applicationContext)
//            Toast.makeText(mContext, "???????????? ???????????????.", Toast.LENGTH_SHORT).show()
//            DeleteTokenTask(mContext, mOAuthLoginModule!!).execute()
//        }
//    }
//
//    inner class DeleteTokenTask(
//        private val mContext: Context,
//        private val mOAuthLoginModule: OAuthLogin
//    ) :
//        AsyncTask<Void?, Void?, Boolean>() {
//         override fun doInBackground(vararg p0: Void?): Boolean? {
//            val isSuccessDeleteToken = mOAuthLoginModule.logoutAndDeleteToken(mContext)
//            if (!isSuccessDeleteToken) {
//                // ???????????? ?????? ????????? ??????????????? ?????????????????? ?????? ????????? ???????????? ??????????????? ???????????????.
//                // ?????????????????? ?????? ????????? ?????? ????????? ????????? ????????? ??? ?????? ????????? ????????????.
//                Log.d(TAG, "errorCode:" + mOAuthLoginModule.getLastErrorCode(mContext))
//                Log.d(TAG, "errorDesc:" + mOAuthLoginModule.getLastErrorDesc(mContext))
//            }
//            return isSuccessDeleteToken
//        }
//
//        override fun onPostExecute(isSuccessDeleteToken: Boolean) {}
//    }

//   fun Login() {
//
//       loginBtn.setOnClickListener {
//
//           if(idArea2.text.isNullOrBlank() || passwordArea2.text.isNullOrBlank()) {
//               Toast.makeText(this, "???????????? ??????????????? ???????????????", Toast.LENGTH_SHORT).show()
//           } else {
//               MySharedPreferences.setUserId(this, idArea2.text.toString())
//               MySharedPreferences.setUserPass(this, passwordArea2.text.toString())
//               Toast.makeText(this, "${MySharedPreferences.getUserId(this)}??? ????????? ???????????????.", Toast.LENGTH_SHORT).show()
//               var intent = Intent(this, HomeActivity::class.java)
//               startActivity(intent)
//               finish()
//           }
//       }
//   }




}
