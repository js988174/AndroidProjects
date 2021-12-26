package com.mandeum.dessert39.Login.ServerApi

import android.util.Log
import android.widget.EditText
import com.mandeum.dessert39.CBC.Cbc
import com.mandeum.dessert39.Login.ServerApi.Model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import com.mandeum.dessert39.Login.ServerApi.Model.LoginModel as LoginModel1

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

        fun logout(TOKEN: String): LogoutModel {
            val code = "<CMD>logout</CMD><DATA><TOKEN>$TOKEN</TOKEN></DATA>"
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
                return Json.logoutJSONParse(decryptedString)
            } else {
                return LogoutModel(errCode = "")
            }
        }

        fun findId(NAME: String, PHONE: String): FindIdModel {
            val code = "<CMD>find_id</CMD><DATA><NAME>$NAME</NAME><PHONE>$PHONE</PHONE></DATA>"
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
                return Json.findId(decryptedString)
            } else {
                return FindIdModel(errCode = "")
            }
        }

        fun sendSms(HPNUM: String, CONTENT: String): SmsModel {

            val code = "<CMD>send_sms</CMD><DATA><HPNUM>$HPNUM</HPNUM><CONTENT>$CONTENT</CONTENT></DATA>"

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

                val smsModel: SmsModel = Json.sendSms(decryptCbc)


                httpClient.disconnect()

                return smsModel
            } else {
                val smsModel: SmsModel = SmsModel("", "", "","")
                httpClient.disconnect()
                return smsModel
            }
        }




        fun Snslogin(ID: String, EMAIL: String, NAME: String, PHONE: String, BIRTH: String, UUID: String): SnsLoginModel {

            val code = " <CMD>sns_login</CMD><DATA><ID>$ID</ID><EMAIL>$EMAIL</EMAIL><NAME>$NAME</NAME><PHONE>$PHONE</PHONE><BIRTH>$BIRTH</BIRTH><UUID>$UUID</UUID><OS>android</OS><SNS>N</SNS></DATA>"

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

                val snsModel: SnsLoginModel = Json.SnsloginJSONParse(decryptCbc)


                httpClient.disconnect()

                return snsModel
            } else {
                val snsModel: SnsLoginModel = SnsLoginModel("", "", "","")
                httpClient.disconnect()
                return snsModel
            }
        }

        fun Snslogin2(ID: String, EMAIL: String, NAME: String, PHONE: String, BIRTH: String, UUID: String): SnsLoginModel {

            val code = " <CMD>sns_login</CMD><DATA><ID>$ID</ID><EMAIL>$EMAIL</EMAIL><NAME>$NAME</NAME><PHONE>$PHONE</PHONE><BIRTH>$BIRTH</BIRTH><UUID>$UUID</UUID><OS>android</OS><SNS>K</SNS></DATA>"

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

                val snsModel: SnsLoginModel = Json.SnsloginJSONParse(decryptCbc)


                httpClient.disconnect()

                return snsModel
            } else {
                val snsModel: SnsLoginModel = SnsLoginModel("", "", "","")
                httpClient.disconnect()
                return snsModel
            }
        }


        fun CheckId(userId: String): String {
            val code = "<CMD>chk_id</CMD><DATA><ID>$userId</ID></DATA>"
            val Ase256: String = Cbc.encryptCBC(code)
            val EncodeUrl = URLEncoder.encode(Ase256, "UTF-8")
            val resultUrl: String = serverUrl + EncodeUrl
            val url = URL(resultUrl)
            val httpClient: HttpURLConnection = url.openConnection() as HttpURLConnection

            httpClient.requestMethod = "GET"
            httpClient.doOutput = true
            httpClient.doInput = true
//            httpClient.connectTimeout = 10000
//            httpClient.readTimeout = 10000

            try {

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
                    val check: String = Json.checkId(decryptedString)

                    httpClient.disconnect()
                    return check
                } else {

                    httpClient.disconnect()
                    return "false"
                }
            } catch (e: Exception) {
                    e.printStackTrace()
                }
                return "false"
        }

        fun term(part: String): TermModel {
            val code = "<CMD>terms</CMD><DATA><PART>$part</PART></DATA>"
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
                return Json.termJSONParse(decryptedString, part)
            } else {
                return TermModel(connection = false, errCode = "", term = "")
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

        fun orderBanner(): OrderBannerModel {
            val code = "<CMD>get_banner</CMD><DATA><PART>O</PART></DATA>"
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

            val bannerList = ArrayList<String>()

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
                return Json.orderBannerJSONParse(decryptedString)
            } else {
                return OrderBannerModel(connection = false, errCode = "", Arraylist = bannerList)
            }
        }




        fun Category(): CategoryModel {
            val code = "<CMD>goods_cate</CMD>"
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
                return Json.categoryJSONParse(decryptedString)
            } else {
                return CategoryModel(connection = false, errCode = "", list = ArrayList())
            }
        }

        fun dessertList(): DessertListModel {
            val code =     "<CMD>get_goods_list</CMD><DATA><CATE>1</CATE><CATE2></CATE2><NAME></NAME></DATA>"
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
                return Json.desertListJSONParse(decryptedString)
            } else {
                return DessertListModel(connection = false, errCode = "", list = ArrayList())
            }
        }

        fun menuList(category: Int): MenuListModel {
            val code = "<CMD>get_goods_list</CMD><DATA><CATE>$category</CATE><CATE2></CATE2><NAME></NAME></DATA>"
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
                return Json.menuListJSONParse(decryptedString)
            } else {
                return MenuListModel(connection = false, errCode = "", list = ArrayList())
            }
        }

    }
}