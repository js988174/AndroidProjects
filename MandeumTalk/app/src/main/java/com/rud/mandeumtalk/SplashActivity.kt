package com.rud.mandeumtalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.auth.IntroActivity
import com.rud.mandeumtalk.auth.LoginActivity

class SplashActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		auth = Firebase.auth

		var lodingImage = findViewById(R.id.loading_image) as LottieAnimationView

		lodingImage.playAnimation()

		if(auth.currentUser?.uid == null) {
			Log.d("Splash Activity", "null")

			Handler().postDelayed({
				startActivity(Intent(this, LoginActivity::class.java))
				finish()
			},2500)

		} else {
			Log.d("Splash Activity", "not null")

			Handler().postDelayed({
				startActivity(Intent(this, MainActivity::class.java))
				finish()
			},2500)
		}


	}
}