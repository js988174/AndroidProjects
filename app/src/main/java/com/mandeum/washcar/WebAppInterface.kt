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
    fun myLat(lat : String) {
        Toast.makeText(mContext, ""+lat, Toast.LENGTH_SHORT).show()

    }

    interface BridgeListener {
        fun showToast(msg: String)
    }
}