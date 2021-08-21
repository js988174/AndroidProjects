package com.rud.mandeumtalk.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.databinding.ActivityIntroBinding
import kotlinx.android.synthetic.main.activity_intro.*


class IntroActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	private lateinit var binding : ActivityIntroBinding

	// onBackPressed()
	private var isDouble = false

	// googleLogin 관리
	var googleSignInClient: GoogleSignInClient? = null

	//GoogleLogin
	val GOOGLE_LOGIN_CODE = 9001 // Intent Request ID


	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		auth = Firebase.auth

		//구글 로그인 옵션
		var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestIdToken(getString(R.string.default_web_client_id))
			//486741499598-2dhe1kflsraa90qe5g7sj3mck9vlbkbo.apps.googleusercontent.com
			.requestEmail()
			.build()
		//구글 로그인 클래스를 만듬
		googleSignInClient = GoogleSignIn.getClient(this, gso)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)


		// 카카오 오류 표시

		val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
			if (error != null) {
				when {
					error.toString() == AuthErrorCause.AccessDenied.toString() -> {
						Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
					}
					error.toString() == AuthErrorCause.InvalidClient.toString() -> {
						Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
					}
					error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
						Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
					}
					error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
						Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
					}
					error.toString() == AuthErrorCause.InvalidScope.toString() -> {
						Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
					}
					error.toString() == AuthErrorCause.Misconfigured.toString() -> {
						Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
					}
					error.toString() == AuthErrorCause.ServerError.toString() -> {
						Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
					}
					error.toString() == AuthErrorCause.Unauthorized.toString() -> {
						Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
					}
					else -> { // Unknown
						Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
					}
				}
			}
			else if (token != null) {
				Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
				val intent = Intent(this, MainActivity::class.java)
				intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
				startActivity(intent)
			}
		}

		// 카카오 버튼

		binding.kakaoBtn.setOnClickListener {
			if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
				UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
			}else{
				UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
			}
		}


		// 로그인
		binding.loginBtn.setOnClickListener {
			val intent = Intent(this, LoginActivity::class.java)
			intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
			startActivity(intent)
		}

		// 회원 가입
		binding.joinBtn.setOnClickListener {
			val intent = Intent(this, JoinActivity::class.java)
			intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
			intent.putExtra("Activity", "Intro")
			startActivity(intent)
		}

//		// Board - 기능 미사용 삭제 (현승_2021.08.14)
//		binding.goBoard.setOnClickListener {
//			val intent = Intent(this, BoardActivity::class.java)
//			intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//			startActivity(intent)
//		}

		// 구글 로그인
		binding.googleBtn.setOnClickListener {
			var signInIntent = googleSignInClient?.signInIntent
			startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
		}

		// 비회원 가입
		binding.noAcouuntBtn.setOnClickListener {
			auth.signInAnonymously()
				.addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {
						val intent = Intent(this, MainActivity::class.java)
						intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
						startActivity(intent)

						Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

					} else {
						Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
					}

				}
		}

		// Naver Login Button
		binding.naverLoginButton.setOnClickListener {
			Toast.makeText(this, "Naver Login is not Ready", Toast.LENGTH_SHORT).show()
		}

		// Facebook Login Button
		binding.facebookLoginButton.setOnClickListener {
			Toast.makeText(this, "Facebook Login is not Ready", Toast.LENGTH_SHORT).show()
		}
	}



	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
		if (requestCode == GOOGLE_LOGIN_CODE && resultCode == Activity.RESULT_OK) {
			val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
			if (result.isSuccess) {
				// Google Sign In was successful, authenticate with Firebase
				val account = result.signInAccount
				firebaseAuthWithGoogle(account!!)
			}

		}
	}


	private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
		val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
		auth.signInWithCredential(credential)
			.addOnCompleteListener(this) { task ->
				if (task.isSuccessful) {
					val intent = Intent(this, MainActivity::class.java)
					intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
					startActivity(intent)

					Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

				} else {

					Toast.makeText(this, "로그인 실패" , Toast.LENGTH_LONG).show()

				}
			}
	}

	override fun onBackPressed(){

		if(isDouble == true) {
			finish()
		}

		isDouble = true
		Toast.makeText(this, "Are your sure Exit?", Toast.LENGTH_LONG).show()

		Handler().postDelayed(Runnable {isDouble = false}, 2000)
	}

//	override fun onStart() {
//		super.onStart()
//
//		//자동 로그인 설정
//		moveMainPage(auth?.currentUser)
//
//	}

}