package com.rud.mandeumtalk

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		KakaoSdk.init(this, "a58f25b539a64355cfd9e75cfbd6b217")
	}
}