package com.mandeum.kidforp

import android.content.Context
import android.os.Handler
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast

class WebAppInterface() {

    private var callback: BridgeListener? = null

    fun setListener(listener: BridgeListener) {
        callback = listener
    }

    private val handler: Handler = Handler()


    @JavascriptInterface
    fun login() {
        handler.post(Runnable() {
            callback?.androidLogin()
        })
    }

    interface BridgeListener {
        fun androidLogin()
    }
}