package com.rud.mandeumtalk.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.databinding.ActivityJoinBinding
import com.rud.mandeumtalk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	private lateinit var binding: ActivityLoginBinding


	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		auth = Firebase.auth

		binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


		// 로그인
		binding.loginBtn.setOnClickListener {

			val email = binding.emailArea.text.toString()
			val password = binding.passwordArea.text.toString()

			auth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {

						val intent = Intent(this, MainActivity::class.java)
						intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
						startActivity(intent)

						Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

					} else {

						Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()

					}
				}

		}
	}
}