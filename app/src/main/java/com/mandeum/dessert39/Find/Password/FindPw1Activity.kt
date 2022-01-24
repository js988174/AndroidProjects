package com.mandeum.dessert39.Find.Password

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.ViewGroup
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.Main.My39.Info.MyInfoFragment



import com.mandeum.dessert39.R
import com.mandeum.dessert39.WebAppInterface
import kotlinx.android.synthetic.main.activity_find1.*
import kotlinx.android.synthetic.main.activity_find2.*
import kotlinx.android.synthetic.main.activity_find_pw.*
import java.net.URISyntaxException

class FindPw1Activity : AppCompatActivity(), WebAppInterface.BridgeListener {

    private lateinit var webView: WebView
    private val bridge = WebAppInterface()
    private lateinit var dialog : Dialog
    private val context : Context
        get() {
            TODO()
        }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pw)


        webView = findViewById(R.id.webView1)


        webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            settings.loadsImagesAutomatically = true
            settings.builtInZoomControls = true
            settings.setSupportZoom(true)
            settings.setSupportMultipleWindows(true)
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            webChromeClient = object : WebChromeClient() {
                override fun onCreateWindow(
                    view: WebView?,
                    isDialog: Boolean,
                    isUserGesture: Boolean,
                    resultMsg: Message?
                ): Boolean {
                    val newWebView = WebView(this@FindPw1Activity).apply {
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.javaScriptCanOpenWindowsAutomatically = true
                        settings.cacheMode = WebSettings.LOAD_NO_CACHE
                        settings.loadsImagesAutomatically = true
                        settings.builtInZoomControls = true
                        settings.setSupportZoom(true)
                        settings.setSupportMultipleWindows(true)
                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        try {
                            val cookieManager: CookieManager = CookieManager.getInstance()
                            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            cookieManager.setAcceptCookie(true)
                            cookieManager.setAcceptThirdPartyCookies(this, true)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        addJavascriptInterface(bridge, "Android")
                        bridge.setListener(this@FindPw1Activity)
                        webChromeClient = WebChromeClient()
                        webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean {
                                val url: String = request?.url?.toString() ?: ""
                                if (url.startsWith("intent://")) {
                                    var intent: Intent? = null
                                    try {
                                        intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                                        if (intent != null) {

                                            startActivity(intent)
                                        }
                                    } catch (e: URISyntaxException) {

                                        e.printStackTrace()
                                    } catch (e: ActivityNotFoundException) {

                                        e.printStackTrace()
                                        val packageName = intent?.`package`
                                        if (!packageName.equals("")) {
                                            startActivity(
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse("market://details?id=$packageName")
                                                )
                                            )
                                        }
                                    }
                                    return true
                                } else if (
                                    url.startsWith("https://play.google.com/store/apps/details?id=") ||
                                    url.startsWith("market://details?id=")
                                ) {
                                    val uri: Uri = Uri.parse(url)
                                    val packageName = uri.getQueryParameter("id")
                                    if (packageName != null && packageName == "") {
                                        startActivity(
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse("market://details?id=$packageName")
                                            )
                                        )
                                    }
                                    return true
                                }
                                return false
                            }
                        }
                    }
                     dialog = Dialog(this@FindPw1Activity).apply {
                        setContentView(newWebView)
                        window!!.attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
                        window!!.attributes.height = ViewGroup.LayoutParams.MATCH_PARENT
                        show()
                    }
                    webChromeClient = object : WebChromeClient() {
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
            val cookieManager: CookieManager = CookieManager.getInstance()
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            cookieManager.setAcceptCookie(true)
            cookieManager.setAcceptThirdPartyCookies(webView, true)
            webView.loadUrl("http://dessert39.com/plugin/nice_auth/auth_pw.php?os=A")
            addJavascriptInterface(bridge, "Android")
            bridge.setListener(this@FindPw1Activity)
        }


    }




    override fun auth_return(p1: String, p2: String, p3: String, p4: String, p5: String) {
        if (p4 == "0") {
//            val intent = Intent(this, FindPw2Activity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
//            intent.putExtra("userID", p5)
//            startActivity(intent)

//            val fragment = MyInfoFragment()
//            val bundle = bundleOf("key1" to "010-2221-4906")
//            fragment.arguments = bundle

            dialog.dismiss()

        } else {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
            startActivity(intent)

        }
      }


    }


