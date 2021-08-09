package com.rud.mandeumtalk.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.board.BoardActivity
import com.rud.mandeumtalk.databinding.ActivityIntroBinding
import kotlinx.android.synthetic.main.activity_intro.*


class IntroActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	private lateinit var binding : ActivityIntroBinding

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

		// 로그인
		binding.loginBtn.setOnClickListener {
			val intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
		}

		// 회원 가입
		binding.joinBtn.setOnClickListener {
			val intent = Intent(this, JoinActivity::class.java)
			startActivity(intent)
		}

		// Board
		binding.goBoard.setOnClickListener {
			val intent = Intent(this, BoardActivity::class.java)
			startActivity(intent)
		}

		// 구글 로그인
		binding.googleBtn.setOnClickListener {
			val intent = Intent(this, MainActivity::class.java)
			startActivity(intent)
		}

		// 비회원 가입
		binding.noAcouuntBtn.setOnClickListener {
			auth.signInAnonymously()
				.addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {

						Toast.makeText(this, "로그인 성공" , Toast.LENGTH_LONG).show()

						val intent = Intent(this, MainActivity::class.java)
						intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
						startActivity(intent)

					} else {

						Toast.makeText(this, "로그인 실패" , Toast.LENGTH_LONG).show()

					}
				}
		}
	}

//	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//		super.onActivityResult(requestCode, resultCode, data)
//
//		// 구글에서 승인된 정보를 가지고 오기
//		if (requestCode == GOOGLE_LOGIN_CODE && resultCode == Activity.RESULT_OK) {
//
//			val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
//			println(result.status.toString())
//			if (result.isSuccess) {
//				val account = result.signInAccount
//				firebaseAuthWithGoogle(account!!)
//			} else {
//				progress_bar.visibility = View.GONE
//			}
//		}
//	}
//
//	fun googleLogin() {
//		progress_bar.visibility = View.VISIBLE
//		var signInIntent = googleSignInClient?.signInIntent
//		startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
//	}
//
//	fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
//		val credential = GoogleAuthProvider.getCredential(account.idToken, null)
//		auth?.signInWithCredential(credential)
//			?.addOnCompleteListener { task ->
//				progress_bar.visibility = View.GONE
//
//			}
//	}


}