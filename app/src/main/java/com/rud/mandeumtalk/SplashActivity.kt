package com.rud.mandeumtalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.rud.mandeumtalk.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		var lodingImage = findViewById(R.id.loading_image) as LottieAnimationView

		lodingImage.playAnimation()

		Handler().postDelayed({
			startActivity(Intent(this, IntroActivity::class.java))
			finish()
		},4000)

	}
}