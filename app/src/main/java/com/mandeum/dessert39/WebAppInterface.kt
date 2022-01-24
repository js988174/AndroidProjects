package com.mandeum.dessert39

import android.content.Context
import android.os.Handler
import android.webkit.JavascriptInterface


class WebAppInterface() {


        private var callback: BridgeListener? = null
        private var handler = Handler()



        fun setListener(listener: BridgeListener) {
            callback = listener
        }

    @JavascriptInterface
    fun auth_return(p1: String, p2: String, p3: String, p4: String, p5: String) {
        handler.post {
            callback?.auth_return(p1, p2, p3, p4, p5)
        }
    }

    interface BridgeListener {
        fun auth_return(p1: String, p2: String, p3: String, p4: String, p5: String)
    }

}