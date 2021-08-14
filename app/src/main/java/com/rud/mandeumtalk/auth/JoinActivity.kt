package com.rud.mandeumtalk.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.OnBackPress
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.board.BoardAdapter
import com.rud.mandeumtalk.board.OnBoardItemClickListener
import com.rud.mandeumtalk.databinding.ActivityJoinBinding


class JoinActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	private lateinit var binding : ActivityJoinBinding

//	private var activity : String = intent.getStringExtra("Activity").toString()

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		auth = Firebase.auth


		binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

		binding.joinBtn.setOnClickListener {

			var isGoToJoin = true

			val email = binding.emailArea.text.toString()
			val password1 = binding.passwordArea1.text.toString()
			val password2 = binding.passwordArea2.text.toString()
			val emailLink = intent.data.toString()

			if(email.isEmpty()) {
				Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
				isGoToJoin = false
			}

			if(password1.isEmpty()) {
				Toast.makeText(this, "Password1을 입력해주세요", Toast.LENGTH_LONG).show()
				isGoToJoin = false
			}

			if(password2.isEmpty()) {
				Toast.makeText(this, "Password2을 입력해주세요", Toast.LENGTH_LONG).show()
				isGoToJoin = false
			}

			// 비밀번호 2개가 같은지 확인
			if(!password1.equals(password2)) {
				Toast.makeText(this, "비밀번호를 똑같이 입력해주세요", Toast.LENGTH_LONG).show()
				isGoToJoin = false
			}

			// 비밀번호가 6자 이상인지
			if (password1.length < 6) {
				Toast.makeText(this, "비밀번호를 6자리 이상으로 입력해주세요", Toast.LENGTH_LONG).show()
				isGoToJoin = false
			}


			if(isGoToJoin) {

				auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {

						Toast.makeText(this, "Thank You for Join Us", Toast.LENGTH_LONG).show()

						val intent = Intent(this, MainActivity::class.java)
						intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
						startActivity(intent)


					} else {
						Toast.makeText(this, "Fail Join Us Process", Toast.LENGTH_LONG).show()
					}
				}

			}

		}

		val activity : String = intent.getStringExtra("Activity").toString()
		Log.e("Activity (Hyeonseung)", activity)

//		if (activity == "Login") {
//
//
//			val onBackPress = object : OnBackPress {
//
//				override fun onBackPressed() {
//					Toast.makeText(this@JoinActivity, "안되는가", Toast.LENGTH_SHORT).show()
//					val intent = Intent(this@JoinActivity, LoginActivity::class.java)
//					startActivity(intent)
//				}
//			}
//		}
//		if (activity == "Intro") {
//			val onBackPress = object : OnBackPress {
//
//				override fun onBackPressed() {
//					Toast.makeText(this@JoinActivity, "안되는가", Toast.LENGTH_SHORT).show()
//					val intent = Intent(this@JoinActivity, IntroActivity::class.java)
//					startActivity(intent)
//				}
//			}
//		}
	}

	override fun onBackPressed() {

		val activity = intent.getStringExtra("Activity").toString()
		Log.d("Hyeonseung_activity", "$activity")

		if(activity == "Login") {
			val intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
		}
		if(activity == "Intro") {
			val intent = Intent(this, IntroActivity::class.java)
			startActivity(intent)
		}
	}

//		Log.e("Hyeonseung - activity : ", "$activity")
//
//		if (activity == "Login") {
//			val intent = Intent(this, LoginActivity::class.java)
//			startActivity(intent)
//		}
//		if (activity == "Intro") {
//			val intent = Intent(this, IntroActivity::class.java)
//			startActivity(intent)
//		}
}