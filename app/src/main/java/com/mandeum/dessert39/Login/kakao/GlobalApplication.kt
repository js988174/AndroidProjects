package com.mandeum.dessert39.Login.kakao

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.mandeum.dessert39.R

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }

}