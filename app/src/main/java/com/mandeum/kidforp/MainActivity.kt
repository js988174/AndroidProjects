package com.mandeum.kidforp

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.io.File
import java.net.URISyntaxException

class MainActivity : AppCompatActivity(), WebAppInterface.BridgeListener {

    private lateinit var webView: WebView
    private val bridge = WebAppInterface()
    var loadingFinished : Boolean = true
    val PROTOCOL_START = "intent:"

    private var filePathCallbackLollipop: ValueCallback<Array<Uri>>? = null
    private var filePathCallbackNormal: ValueCallback<Uri?>? = null
    private var imageUri: Uri? = null
    val FILECHOOSER_NORMAL_REQ_CODE = 2001
    val FILECHOOSER_LOLLIPOP_REQ_CODE = 2002

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView1)

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
        }

        webView.settings.javaScriptEnabled = true
        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        val cookieManager: CookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(webView, true)
        webView.loadUrl("https://www.kidforp.co.kr/")
        webView.webViewClient = WebViewClientClass()
        webView.addJavascriptInterface(bridge, "android")
        webView.settings.textZoom = 100
        bridge.setListener(this)
    }

    private var mWebViewListener: WebViewListener? = null



    inner class WebViewClientClass : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            mWebViewListener?.onPageStarted(url, favicon)
            loadingFinished = false

        }

        @SuppressLint("MissingPermission")
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            mWebViewListener?.onPageFinished(url)
            loadingFinished = true

        }



        override fun onLoadResource(view: WebView?, url: String?) {
            mWebViewListener?.onLoadResource(url)
            super.onLoadResource(view, url)
        }

        override fun onPageCommitVisible(view: WebView?, url: String?) {
            super.onPageCommitVisible(view, url)
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



    private fun showDialogToGetPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("권한 설정")
            .setMessage("권한 설정이 거부되었습니다. " +
                    "권한 설정을 하로 가시겠습니까?")

        builder.setPositiveButton("확인") { dialogInterface, i ->
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", "com.mandeum.kidforp", null))
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
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

    override fun onDestroy() {
        super.onDestroy()
        webView.clearHistory()
        webView.onPause()
    }

    override fun androidMyLatitude() {

    }

    override fun androidLogin() {

    }
}