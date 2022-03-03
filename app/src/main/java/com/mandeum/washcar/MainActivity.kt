package com.mandeum.washcar

import android.Manifest
import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.*
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
import androidx.annotation.RequiresApi
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.net.URISyntaxException
import java.util.*
import android.content.Intent
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.os.Build
import android.provider.MediaStore
import java.io.File
import android.os.Parcelable
import android.os.Environment
import android.annotation.TargetApi
import android.widget.Toast


class MainActivity : AppCompatActivity(), WebAppInterface.BridgeListener {

    var lat : Double = 0.0
    var lng : Double = 0.0
    private val bridge = WebAppInterface()
    val PROTOCOL_START = "intent:"


    private var filePathCallbackLollipop: ValueCallback<Array<Uri>>? = null
    private var filePathCallbackNormal: ValueCallback<Uri?>? = null
    private var imageUri: Uri? = null
    val FILECHOOSER_NORMAL_REQ_CODE = 2001
    val FILECHOOSER_LOLLIPOP_REQ_CODE = 2002

    private var count: Int = 0
    private var first: Boolean = true
    var loadingFinished : Boolean = true

    private val permissionCode: Int = 100
    private var isPermissionOK: Boolean = false

    private var token : String? = MyApplication.prefs.getString("token", "")

    val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var webView: WebView
    private lateinit var mProgressBar: ProgressBar

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission()

        val isFirst = MyApplication.prefs.getBoolean("isFirst", true)

        if (isFirst) {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), permissionCode)
                MyApplication.prefs.setBoolean("isFirst", false)
            }
        } else if (!isFirst) {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                isPermissionOK = false
                showDialogToGetPermission()
            } else {
                isPermissionOK = true
            }
        }
        try {
            if (isPermissionOK) {
                val locationManager: LocationManager =
                    getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val isNetworkEnabled: Boolean =
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                val isGPSEnabled: Boolean =
                    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

                val locationListener = LocationListener { p0 ->
                    lat = p0.latitude
                    lng = p0.longitude

                    count += 1
                }

                if (isNetworkEnabled) {
                    val location: Location? =
                        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (location != null) {
                        lat = location.latitude
                        lng = location.longitude
                    } else {
                        locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            1L,
                            1F,
                            locationListener
                        )
                        locationManager.removeUpdates(locationListener)
                    }
                } else if (isGPSEnabled) {
                    val location: Location? =
                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (location != null) {
                        lat = location.latitude
                        lng = location.longitude
                    } else {
                        locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            1L,
                            1F,
                            locationListener
                        )
                        locationManager.removeUpdates(locationListener)
                    }
                }
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        } finally {
            requestPermission()
        }


        webView = findViewById(R.id.webView1)
        mProgressBar = findViewById(R.id.progress1)

        webView.apply {
            webChromeClient = object : WebChromeClient() {
                override fun onJsConfirm(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    result: JsResult?
                ): Boolean {
                    androidx.appcompat.app.AlertDialog.Builder(this@MainActivity)
                        .setTitle("알림")
                        .setMessage(message)
                        .setPositiveButton("확인") { _, _ ->
                        }
                        .setNegativeButton("취소") { _, _ ->
                        }
                        .create()
                        .show()
                    return true
                }

                override fun onShowFileChooser(
                    webView: WebView?,
                    filePathCallback: ValueCallback<Array<Uri>>,
                    fileChooserParams: FileChooserParams?
                ): Boolean {
                    if (filePathCallbackLollipop != null) {
                        filePathCallbackLollipop!!.onReceiveValue(null)
                        filePathCallbackLollipop = null
                    }
                    filePathCallbackLollipop = filePathCallback

                    val isCapture = fileChooserParams!!.isCaptureEnabled

                    runCamera(isCapture, 1)
                    return true
                }
            }

//            settings.javaScriptEnabled = true
//            settings.setSupportMultipleWindows(false) // 새창 띄우기
//            settings.setSupportZoom(false) // 화면 확대 허용
//            settings.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기 허용
//            settings.loadWithOverviewMode = true // html의 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
//            settings.useWideViewPort = true // html의 viewport 메타 태그 지원
//            settings.builtInZoomControls = false // 화면 확대/축소 허용
//            settings.displayZoomControls = false
//            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
//            settings.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐쉬 허용
//            settings.domStorageEnabled = true // 로컬 저장 허용
//            settings.databaseEnabled = true
//            settings.mediaPlaybackRequiresUserGesture = false
//            settings.allowContentAccess = true
//            settings.setGeolocationEnabled(true)
//            settings.allowUniversalAccessFromFileURLs = true
//            settings.allowFileAccess = true
//            fitsSystemWindows = true


        }

        webView.settings.javaScriptEnabled = true
        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        val cookieManager: CookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(webView, true)
        webView.loadUrl("http://carwashday.com/")
        webView.webViewClient = WebViewClientClass()
        webView.addJavascriptInterface(bridge, "android")
        bridge.setListener(this)

    }

    private var mWebViewListener: WebViewListener? = null



    inner class WebViewClientClass : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            mWebViewListener?.onPageStarted(url, favicon)
            webView.loadUrl("javascript:my_lat(${lat},${lng})")
            loadingFinished = false
        }

        @SuppressLint("MissingPermission")
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            mWebViewListener?.onPageFinished(url)


                val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val isGPSEnabled : Boolean = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
                val isNetworkEnabled: Boolean = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                first = false
            try {
                if (Build.VERSION.SDK_INT >= 26 && ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        0
                    )
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
            } catch (e : SecurityException) {
                e.printStackTrace()
            } finally {
                requestPermission()
            }

                loadingFinished = true

            if (first) {
                webView.loadUrl("javascript:get_my_lat(${lat},${lng})")
                first = false
            }
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
            val url: String = request?.url.toString()

            try {
                if (url.startsWith(PROTOCOL_START) ||
                    url.contains("market://") ||
                    url.contains("kr.co.samsungcard.mpocket") ||
                    url.contains("com.shcard.smartpay") ||
                    url.contains("com.shinhancard.smartshinhan") ||
                    url.contains("com.kbcard.cxh.appcard") ||
                    url.contains("com.kbstar.liivbank") ||
                    url.contains("com.kbstar.reboot") ||
                    url.contains("kvp.jjy.MispAndroid320") ||
                    url.contains("com.hanaskcard.rocomo.potal") ||
                    url.contains("com.hanaskcard.paycla") ||
                    url.contains("kr.co.hanamembers.hmscustomer") ||
                    url.contains("com.lcacApp") ||
                    url.contains("nh.smart.nhallonepay") ||
                    url.contains("com.wooricard.smartapp") ||
                    url.contains("com.hyundaicard.appcard") ||
                    url.contains("kr.co.citibank.citimobile") ||
                    url.contains("com.kftc.bankpay.android") ||
                    url.contains("kftc-bankpay://") ||
                    url.contains("bankwallet://") ||
                    url.contains("ukbanksmartbanknonloginpay://") ||
                    url.contains("kdb-bankpay://") ||
                    url.contains("ibk-bankpay://") ||
                    url.contains("kb-bankpay://") ||
                    url.contains("keb-bankpay://") ||
                    url.contains("sh-bankpay://") ||
                    url.contains("nhb-bankpay://") ||
                    url.contains("nh-bankpay://") ||
                    url.contains("wr-bankpay://") ||
                    url.contains("sc-bankpay://") ||
                    url.contains("s-bankpay://") ||
                    url.contains("ct-bankpay://") ||
                    url.contains("dg-bankpay://") ||
                    url.contains("bnk-bankpay://") ||
                    url.contains("kj-bankpay://") ||
                    url.contains("jj-bankpay://") ||
                    url.contains("jb-bankpay://") ||
                    url.contains("kn-bankpay://") ||
                    url.contains("kp-bankpay://") ||
                    url.contains("cu-bankpay://") ||
                    url.contains("mg-bankpay://") ||
                    url.contains("kbn-bankpay://") ||
                    url.contains("kkb-bankpay://") ||
                    url.contains("com.kakao.talk") ||
                    url.contains("com.nhnent.payapp") ||
                    url.contains("com.ssg.serviceapp.android.egiftcertificate") ||
                    url.contains("com.sktelecom.tauth") ||
                    url.contains("com.kt.ktauth") ||
                    url.contains("com.kt.ktauthp") ||
                    url.contains("com.lumensoft.touchenappfree") ||
                    url.contains("com.nhn.android.search") ||
                    url.contains("com.samsung.android.spaylite") ||
                    url.contains("com.samsung.android.spay") ||
                    url.contains("com.TouchEn.mVaccine.webs") ||
                    url.contains("com.ahnlab.v3mobileplus") ||
                    url.contains("com.sktelecom.tauth") ||
                    url.contains("com.kt.ktauth") ||
                    url.contains("com.lguplus.smartotp") ||
                    url.contains("com.lottemembers.android") ||
                    url.contains("com.lge.lgpay" ) ||
                    url.contains("kr.co.shiftworks.vguardweb")
                ) {
                    intent = null
                    try {
                        intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)

                    } catch (e: URISyntaxException) {
                        e.printStackTrace()
                        return false
                    }
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                        if (packageManager.resolveActivity(intent, 0) == null) {
                            val pkgName: String? = intent.`package`
                            if (pkgName != null) {
                                val uri: Uri = Uri.parse("market://details?id= $pkgName")
                                intent = Intent(Intent.ACTION_VIEW, uri)
                                startActivity(intent)

                            }
                        } else {
                            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                            val uri: Uri = Uri.parse(intent.dataString)
                            intent = Intent(Intent.ACTION_VIEW, uri)
                            startActivity(intent)

                        }
                    } else {
                        try {
                            startActivity(intent)

                        } catch (e: ActivityNotFoundException) {
                            val uri: Uri = Uri.parse("market://details?id=" + intent.getPackage())
                            intent = Intent(Intent.ACTION_VIEW, uri)
                            startActivity(intent)

                        }
                    }
                } else {
                    view?.loadUrl(url)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }

            if (url.startsWith("tel:")) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                startActivity(intent)
                // reload 안하면 페이지 오류 문구 뜸
                view!!.reload()
                return true
            }
            mWebViewListener?.shouldOverrideUrlLoading(request)
            return true

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
        if (webView.canGoBack()) {
            webView.goBack()
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


    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA,ACCESS_FINE_LOCATION,WRITE_EXTERNAL_STORAGE, ACCESS_COARSE_LOCATION),
            REQUEST_IMAGE_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionCode) {
            if (grantResults.isNotEmpty()) {
                for (item in grantResults) {
                    if (item == PackageManager.PERMISSION_GRANTED) {
                        isPermissionOK = true
                    }
                    if (item != PackageManager.PERMISSION_GRANTED) {
                        isPermissionOK = false
                        showDialogToGetPermission()
                    }
                }
            }
        }
    }

    private fun showDialogToGetPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("권한 설정")
            .setMessage("권한 설정이 거부되었습니다. " +
                    "권한 설정을 하로 가시겠습니까?")

        builder.setPositiveButton("확인") { dialogInterface, i ->
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", "com.mandeum.washcar", null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)   // 6
        }
        builder.setNegativeButton("취소") { dialogInterface, i ->
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

    override fun androidMyLatitude() {
        webView.loadUrl("javascript:get_my_lat('$lat, $lng)'")
        Log.d("java_lat","'$lat,$lng'")
    }

    override fun androidLogin() {
        webView.loadUrl("javascript:get_my_device('android', '$token')")
        webView.loadUrl("javascript:get_my_lat('$lat, $lng)'")
    }


    private fun runCamera(_isCapture: Boolean, selectedType: Int) {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val path = Environment.getExternalStorageDirectory()
        val file = File(path, "temp.png")
        imageUri = Uri.fromFile(file)
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        if (!_isCapture) {
            val pickIntent = Intent(Intent.ACTION_PICK)
            if (selectedType == 1) {
                pickIntent.type = MediaStore.Images.Media.CONTENT_TYPE
                pickIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            } else if (selectedType == 2) {
                pickIntent.type = MediaStore.Video.Media.CONTENT_TYPE
                pickIntent.data = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            }
            val pickTitle = "사진 가져올 방법을 선택하세요."
            val chooserIntent = Intent.createChooser(pickIntent, pickTitle)

            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf<Parcelable>(intentCamera))
            startActivityForResult(chooserIntent, FILECHOOSER_LOLLIPOP_REQ_CODE)
        } else {
            startActivityForResult(intentCamera, FILECHOOSER_LOLLIPOP_REQ_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var data = data
        when (requestCode) {
            FILECHOOSER_NORMAL_REQ_CODE -> if (resultCode == RESULT_OK) {
                if (filePathCallbackNormal == null) return
                val result =
                    if (data == null || resultCode != RESULT_OK) null else data.data
                filePathCallbackNormal!!.onReceiveValue(result)
                filePathCallbackNormal = null
            }
            FILECHOOSER_LOLLIPOP_REQ_CODE -> if (resultCode == RESULT_OK) {
                if (filePathCallbackLollipop == null) return
                if (data == null) data = Intent()
                if (data.data == null) data.data = imageUri
                filePathCallbackLollipop!!.onReceiveValue(
                    WebChromeClient.FileChooserParams.parseResult(
                        resultCode,
                        data
                    )
                )
                filePathCallbackLollipop = null
            } else {
                if (filePathCallbackLollipop != null) {
                    filePathCallbackLollipop!!.onReceiveValue(null)
                    filePathCallbackLollipop = null
                }
                if (filePathCallbackNormal != null) {
                    filePathCallbackNormal!!.onReceiveValue(null)
                    filePathCallbackNormal = null
                }
            }
            else -> {}
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



}


