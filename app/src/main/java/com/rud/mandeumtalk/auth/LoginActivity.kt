package com.rud.mandeumtalk.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.OnBackPress
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.databinding.ActivityJoinBinding
import com.rud.mandeumtalk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	private lateinit var binding: ActivityLoginBinding

	// onBackPressed()
	private var isDouble = false

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		auth = Firebase.auth

		binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

		binding.snsLoginText.setOnClickListener {
			val intent = Intent(this, IntroActivity::class.java)
			startActivity(intent)
		}

		// 로그인
		binding.loginBtn.setOnClickListener {

			val email = binding.emailArea.text.toString()
			val password = binding.passwordArea.text.toString()

			Log.d("email - hyeonseung", email)
			Log.d("password - hyeonseung", password)

			if (email == "" && password == "") {
				Toast.makeText(this, "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
			} else if (email == "" && password != "") {
				Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
			} else if (email != "" && password == "") {
				Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
			} else {
				auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {

						val intent = Intent(this, MainActivity::class.java)
						intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
						startActivity(intent)

					} else {

						Toast.makeText(this, "이메일과 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show()
					}
				}
			}
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

		// Join Us
		binding.joinBtn.setOnClickListener {
			val intent = Intent(this, JoinActivity::class.java)
			intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
			intent.putExtra("Activity", "Login")
			startActivity(intent)
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
}