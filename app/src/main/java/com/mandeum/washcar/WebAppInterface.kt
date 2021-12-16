package com.mandeum.washcar

import android.content.Context
import android.os.Handler
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast

class WebAppInterface(private val mContext: Context) {

    private var callback: BridgeListener? = null
    private var handler = Handler()



    fun setListener(listener: BridgeListener) {
        callback = listener
    }

    @JavascriptInterface
    fun myLat() {
        handler.post(Runnable() {
            callback?.androidMyLatitude()
        })
    }

    @JavascriptInterface
    fun login() {
        handler.post(Runnable() {
            callback?.androidLogin()
        })
    }

    interface BridgeListener {
        fun androidMyLatitude()
        fun androidLogin()
    }
}