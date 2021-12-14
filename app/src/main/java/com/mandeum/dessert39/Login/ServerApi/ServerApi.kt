package com.mandeum.dessert39.Login.ServerApi

import com.mandeum.dessert39.CBC.Cbc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.crypto.spec.SecretKeySpec
import kotlin.concurrent.thread
import com.mandeum.dessert39.Login.ServerApi.LoginModel as LoginModel1

class ServerApi {

    companion object {

        private const val serverUrl = "http://dessert39.man-deum.com/api/index.php?code="

        fun login(userId: String, userPw: String, UUID: String): LoginModel1 {

            val code = " <CMD>login</CMD><DATA><USER_ID>$userId</USER_ID><USER_PWD>$userPw</USER_PWD><UUID>$UUID</UUID><OS>android</OS></DATA>"

            val Ase256: String = Cbc.encryptCBC(code)
            val EncodeUrl = URLEncoder.encode(Ase256, "UTF-8")
            val resultUrl: String = serverUrl + EncodeUrl
            val url = URL(resultUrl)
            val httpClient: HttpURLConnection = url.openConnection() as HttpURLConnection

            httpClient.requestMethod = "GET"
            httpClient.doOutput = true
            httpClient.doInput = true
            httpClient.connectTimeout = 10000
            httpClient.readTimeout = 10000


            if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                val streamReader = InputStreamReader(httpClient.inputStream)
                val buffered = BufferedReader(streamReader)
                val content = StringBuilder()

                while (true) {
                    val line = buffered.readLine() ?: break
                    content.append(line)
                }

                val contentString: String = content.toString()
                val decryptCbc = Cbc.decryptCBC(contentString)

                val loginModel: LoginModel1 = Json.loginJSONParse(decryptCbc)


//                val contentString : String = inputContent.toString()
//
//                val decryptedString : String = EncryptDecrypt.decrypt (contentString)
//
//                val loginDataModel : LoginDataModel = JsonParser.loginParser(decryptedString)

                httpClient.disconnect()

                return loginModel
            } else {
                val loginModel: LoginModel1 = LoginModel1( "", "", "")
                httpClient.disconnect()
                return loginModel
            }
        }

            fun mainBanner(): BannerModel {
                    val code = "<CMD>get_banner</CMD><DATA><PART>M</PART></DATA>"
                    val Ase256: String = Cbc.encryptCBC(code)
                    val EncodeUrl = URLEncoder.encode(Ase256, "UTF-8")
                    val resultUrl: String = serverUrl + EncodeUrl
                    val url = URL(resultUrl)
                    val httpClient: HttpURLConnection = url.openConnection() as HttpURLConnection

                    httpClient.requestMethod = "GET"
                    httpClient.doOutput = true
                    httpClient.doInput = true
                    httpClient.connectTimeout = 10000
                    httpClient.readTimeout = 10000
                if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                    val streamReader = InputStreamReader(httpClient.inputStream)
                    val buffered = BufferedReader(streamReader)
                    val content = StringBuilder()
                    while (true) {
                        val line = buffered.readLine() ?: break
                        content.append(line)
                    }
                    val contentString: String = content.toString()
                    val decryptedString: String = Cbc.decryptCBC(contentString)
                    httpClient.disconnect()
                    return Json.bannerJSONParse(decryptedString)
                } else {
                    return BannerModel(connection = false, errCode = "", banner = "")
                }
            }



    }
}