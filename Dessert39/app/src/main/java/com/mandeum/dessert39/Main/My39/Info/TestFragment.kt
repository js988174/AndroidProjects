package com.mandeum.dessert39.Main.My39.Info

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mandeum.dessert39.R
import com.mandeum.dessert39.WebAppInterface
import com.mandeum.dessert39.databinding.FragmentTestBinding
import java.net.URISyntaxException


class TestFragment : Fragment(), WebAppInterface.BridgeListener{

    private var _binding : FragmentTestBinding? = null
    private val binding get() = _binding!!
    private lateinit var webView: WebView
    private val bridge = WebAppInterface()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestBinding.inflate(layoutInflater)


        webView = binding.webView1

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
                    val newWebView = WebView(requireContext()).apply {
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
                        bridge.setListener(this@TestFragment)
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
                    val dialog = Dialog(requireContext()).apply {
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
            webView.loadUrl("http://dessert39.com/plugin/nice_auth/auth_info.php?os=A")
            addJavascriptInterface(bridge, "Android")
            bridge.setListener(this@TestFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun auth_return(p1: String, p2: String, p3: String, p4: String, p5: String) {
        if (p4 == "0") {
            val intent = TestFragmentDirections.actionTestFragmentToMyInfoFragment()
            findNavController().navigate(intent)
        } else {


        }
    }

}