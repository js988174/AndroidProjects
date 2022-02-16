package com.mandeum.dessert39admin

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import java.net.URISyntaxException


class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var mProgressBar: ProgressBar

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
         if (task.isSuccessful) {
            Log.d("fcm_token", task.result.toString())
         }
     }

        webView = findViewById(R.id.webView1)
        mProgressBar = findViewById(R.id.progress1)

        webView.apply {
            // 팝업창 호출
            webChromeClient = object : WebChromeClient() {

                override fun onCreateWindow(
                    view: WebView?,
                    isDialog: Boolean,
                    isUserGesture: Boolean,
                    resultMsg: Message?
                ): Boolean {
                    val newWebView = WebView(this@MainActivity).apply {
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true
                    }

                    val dialog = Dialog(this@MainActivity).apply {
                        setContentView(newWebView)
                        window!!.attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
                        window!!.attributes.height = ViewGroup.LayoutParams.MATCH_PARENT
                        show()
                    }
                    newWebView.webChromeClient = object : WebChromeClient() {
                        override fun onCloseWindow(window: WebView?) {
                            super.onCloseWindow(window)
                            dialog.dismiss()
                        }
                    }
                    (resultMsg?.obj as WebView.WebViewTransport).webView = newWebView
                    resultMsg.sendToTarget()
                    return true
                }

            }

            settings.javaScriptEnabled = true
            settings.setSupportMultipleWindows(false) // 새창 띄우기
            settings.setSupportZoom(true) // 화면 확대 허용
            settings.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기 허용
            settings.loadWithOverviewMode = true // html의 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
            settings.useWideViewPort = true // html의 viewport 메타 태그 지원
            settings.builtInZoomControls = true // 화면 확대/축소 허용
            settings.displayZoomControls = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
            settings.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐쉬 허용
            settings.domStorageEnabled = true // 로컬 저장 허용
            settings.databaseEnabled = true

//            settings.safeBrowsingEnabled = true

            settings.mediaPlaybackRequiresUserGesture = false

            settings.allowContentAccess = true
            settings.setGeolocationEnabled(true)
            settings.allowUniversalAccessFromFileURLs = true

            settings.allowFileAccess = true
            fitsSystemWindows = true

        }

        webView.loadUrl("http://dessert39.com/adm/app_admin/")
        webView.webViewClient = WebViewClientClass()


    }

    private var mWebViewListener: WebViewListener? = null

    fun setWebViewListener(webViewListener: WebViewListener) {
        mWebViewListener = webViewListener
    }

    inner class WebViewClientClass : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            mWebViewListener?.onPageStarted(url, favicon)

        }

        @SuppressLint("MissingPermission")
        override fun onPageFinished(view: WebView?, url: String?) {
            mWebViewListener?.onPageFinished(url)
            super.onPageFinished(view, url)

        }



        override fun onLoadResource(view: WebView?, url: String?) {
            mWebViewListener?.onLoadResource(url)

            super.onLoadResource(view, url)
        }

        override fun onPageCommitVisible(view: WebView?, url: String?) {
            super.onPageCommitVisible(view, url)
            mProgressBar.visibility = ProgressBar.GONE
            webView.visibility = View.VISIBLE
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            mWebViewListener?.shouldOverrideUrlLoading(request)
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            mWebViewListener?.onReceivedError(request, error)
        }
    }

    override fun onBackPressed() {
        //웹사이트에서 뒤로 갈 페이지 존재시
        if (webView.canGoBack()) {
            webView.goBack() // 웹사이트 뒤로가기
        } else {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("앱을 종료하시겠습니까?")
            builder.setPositiveButton("종료") { _, _ ->
                ActivityCompat.finishAffinity(this)
            }
            builder.setNegativeButton("취소") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }

    interface WebViewListener {
        fun onPageStarted(url: String?, favicon: Bitmap?)
        fun onPageFinished(url: String?)
        fun onLoadResource(url: String?)
        fun shouldOverrideUrlLoading(request: WebResourceRequest?)
        fun onReceivedError(request: WebResourceRequest?, error: WebResourceError?)

    }
}