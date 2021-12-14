package com.mandeum.washcar

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.*
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationRequest.create
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import java.net.URISyntaxException
import java.util.*

class MainActivity : AppCompatActivity(), WebAppInterface.BridgeListener {

    var lat : Double? = null
    var lng : Double? = null

    var first : Boolean = false
    var loadingFinished : Boolean = true


    private lateinit var webView: WebView
    private lateinit var mProgressBar: ProgressBar

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val firstPermission = MyApplication.prefs.getBoolean("firstPermission", true)
//
//        if (firstPermission) {
//            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(
//                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
//                MyApplication.prefs.setBoolean("firstPermission", false)
//            }
//        } else if (!firstPermission) {
//            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                first = false
//                showDialogToGetPermission()
//            } else if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                first = true
//            }
//        }

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
            settings.setSupportZoom(false) // 화면 확대 허용
            settings.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기 허용
            settings.loadWithOverviewMode = true // html의 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
            settings.useWideViewPort = true // html의 viewport 메타 태그 지원
            settings.builtInZoomControls = false // 화면 확대/축소 허용
            settings.displayZoomControls = false
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


        val url = "http://washcar.man-deum.com/"
        webView.loadUrl(url)
        webView.webViewClient = WebViewClientClass()
        webView.addJavascriptInterface(WebAppInterface(this), "android")


    }


    private var mWebViewListener: WebViewListener? = null

    fun setWebViewListener(webViewListener: WebViewListener) {
        mWebViewListener = webViewListener
    }

    inner class WebViewClientClass : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            mProgressBar.visibility = ProgressBar.VISIBLE
            mWebViewListener?.onPageStarted(url, favicon)
            loadingFinished = false
        }

        @SuppressLint("MissingPermission")
        override fun onPageFinished(view: WebView?, url: String?) {
            mWebViewListener?.onPageFinished(url)

            if (!first) {
                val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val isGPSEnabled : Boolean = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
                val isNetworkEnabled: Boolean = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                first = false
                if (Build.VERSION.SDK_INT >= 26 && ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
                } else {
                    when {
                        isNetworkEnabled -> {
                            val location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                            lng = location?.longitude!!
                            lat = location.latitude
                            webView.loadUrl("javascript:my_lat(${lat},${lng})")


                        }
                        isGPSEnabled -> {
                            val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            lng = location?.longitude!!
                            lat = location.latitude
                            webView.loadUrl("javascript:my_lat(${lat},${lng})")

                        }
                        else -> {

                        }
                    }
                }

            } else {
                loadingFinished = true

            }



            webView.loadUrl("javascript:get_my_lat(${lat},${lng})")

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

            var intent : Intent? = null
            val url: String? = request?.url.toString()

            try {
                if (url != null && (url.contains("market://") ||
                            url.contains("kr.co.samsungcard.mpocket") ||
                            url.contains("com.shcard.smartpay") ||
                            url.contains("com.shinhancard.smartshinhan") ||
                            url.contains("com.kbcard.cxh.appcard") ||
                            url.contains("com.kbstar.liivbank") ||
                            url.contains("com.kbstar.reboot") ||
                            url.contains("kvp.jjy.MispAndroid320") ||
                            url.contains("com.ahnlab.v3mobileplus") ||
                            url.contains("com.hanaskcard.paycla") ||
                            url.contains("kr.co.hanamembers.hmscustomer") ||
                            url.contains("com.lcacApp") ||
                            url.contains("nh.smart.nhallonepay") ||
                            url.contains("com.wooricard.smartapp") ||
                            url.contains("com.hyundaicard.appcard") ||
                            url.contains("kr.co.citibank.citimobile") ||
                            url.contains("com.kftc.bankpay.android") ||
                            url.contains("com.kakao.talk") ||
                            url.contains("com. nhnent.payapp") ||
                            url.contains("com.ssg.serviceapp.android.egiftcertificate") ||
                            url.contains("com.sktelecom.tauth") ||
                            url.contains("com.kt.ktauth") ||
                            url.contains("com.kt.ktauthp") ||
                            url.contains("com.hanaskcard.rocomo.potal\n") ||
                            url.contains("com.lumensoft.touchenappfree") ||
                            url.contains("com.TouchEn.mVaccine.webs") ||
                            url.contains("com.ahnlab.v3mobileplus") ||
                            url.contains("kr.co.shiftworks.vguardweb"))

                ) {
                    intent = null

                    try {
                        intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    } catch (e: URISyntaxException) {
                        return false;
                    }
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                        if (MainActivity().packageManager.resolveActivity(intent, 0) == null) {
                            val pkgName: String? = intent.`package`
                            if (pkgName != null) {
                                val uri: Uri = Uri.parse("market://search?q=pname:$pkgName")
                                intent = Intent(Intent.ACTION_VIEW, uri)
                                applicationContext.startActivity(intent)
                            }
                        } else {
                            val uri: Uri = Uri.parse(intent.getDataString())
                            intent = Intent(Intent.ACTION_VIEW, uri)
                            applicationContext.startActivity(intent)
                        }
                    } else {
                        try {
                            applicationContext.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            val uri: Uri = Uri.parse("market://search?q=pname:" + intent.getPackage())
                            intent = Intent(Intent.ACTION_VIEW, uri)
                            applicationContext.startActivity(intent)
                        }
                    }
                } else {
                    view!!.loadUrl(url!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }


            if (!loadingFinished) {
                first = true
            }


            loadingFinished = false
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
    // 주기적으로 사용할 시시
//    val gpsLocatonListener = object : LocationListener {
//        override fun onLocationChanged(location: Location) {
//           val provider: String = location.provider
//            val lat: Double = location.longitude
//            val lng: Double = location.longitude
//        }
//
//    }
    private var handler = Handler()

    @JavascriptInterface
    fun get_my_lat(lat: String, lng: String) {
        handler.post {
            webView.loadUrl("javascript:my_lat(${lat},${lng})")

        }
    }




    private fun showDialogToGetPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("위치 설정")
            .setMessage("위치 설정이 거부되었습니다. " +
                    "위치 설정을 하로 가시겠습니까?")

        builder.setPositiveButton("확인") { dialogInterface, i ->
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", "com.mandeum.washcar", null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)   // 6
        }
        builder.setNegativeButton("취소") { dialogInterface, i ->
            // ignore
        }
        val dialog = builder.create()
        dialog.show()
    }


    interface WebViewListener {
        fun onPageStarted(url: String?, favicon: Bitmap?)
        fun onPageFinished(url: String?)
        fun onLoadResource(url: String?)
        fun shouldOverrideUrlLoading(request: WebResourceRequest?)
        fun onReceivedError(request: WebResourceRequest?, error: WebResourceError?)

    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }



}


