package com.mandeum.dessert39.Login.ServerApi

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import com.mandeum.dessert39.CBC.Cbc
import com.mandeum.dessert39.Login.ServerApi.Model.*
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardEventModel
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardListModel
import com.mandeum.dessert39.Login.ServerApi.Model.Board.SetBoardModel
import com.mandeum.dessert39.Login.ServerApi.Model.Card.CardChoiceModel
import com.mandeum.dessert39.Login.ServerApi.Model.Home.BannerModel
import com.mandeum.dessert39.Login.ServerApi.Model.Home.WeatherModel
import com.mandeum.dessert39.Login.ServerApi.Model.Info.UserImageModel
import com.mandeum.dessert39.Login.ServerApi.Model.Login.*
import com.mandeum.dessert39.Login.ServerApi.Model.Order.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection
import com.mandeum.dessert39.Login.ServerApi.Model.Login.LoginModel as LoginModel1


class ServerApi {

    companion object {

        private const val serverUrl = "http://dessert39.com/api/index.php?code="
        private const val LogTag = "HttpAPI.Tag"

        fun login(userId: String, userPw: String, uuid: String): LoginModel1 {

            val code = " <CMD>login</CMD><DATA><USER_ID>$userId</USER_ID><USER_PWD>$userPw</USER_PWD><UUID>$uuid</UUID><OS>android</OS></DATA>"

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
                val loginModel: LoginModel1 = LoginModel1("",  "", "" )
                httpClient.disconnect()
                return loginModel
            }
        }

        fun weatherAPI(city: String, guGun: String): WeatherModel {
            val code =
                "<CMD>weather</CMD><DATA><CITY>$city</CITY><GUGUN>$guGun</GUGUN></DATA>"
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
                    httpClient.disconnect()

                    return Json.weatherParser(decryptedString)

                } else {
                    httpClient.disconnect()
                    return WeatherModel(false,"",  "",  "")
                }
            } catch (e: java.lang.Exception) {
                httpClient.disconnect()

                return WeatherModel(false,"",  "",  "")
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
                return LogoutModel(errCode =  "")
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

        fun MemberReg(ID: String, PWD: String,EMAIL: String, NICK: String, NAME: String, PHONE: String, BIRTH: String, UUID: String): MemberRegModel {

            val code = "<CMD>mem_reg</CMD><DATA><ID>$ID</ID><PWD>$PWD</PWD><EMAIL>$EMAIL</EMAIL><NICK>$NICK</NICK><NAME>$NAME</NAME><PHONE>$PHONE</PHONE><BIRTH>$BIRTH</BIRTH><MARKETING>Y</MARKETING><UUID>$UUID</UUID><OS>android</OS></DATA>"

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

                val memberModel: MemberRegModel = Json.memberReg(decryptCbc)


                httpClient.disconnect()

                return memberModel
            } else {
                val memberModel: MemberRegModel = MemberRegModel(false, "", "")
                httpClient.disconnect()
                return memberModel
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
                val snsModel: SnsLoginModel = SnsLoginModel("", "", "","", "")
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
                val snsModel: SnsLoginModel = SnsLoginModel("", "", "","", "")
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
                httpClient.disconnect()
                return Json.categoryJSONParse(decryptedString)
            } else {
                return CategoryModel(connection = false, errCode = "", list = ArrayList())
            }
            } catch (e: Exception) {
                httpClient.disconnect()
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            return CategoryModel(connection = false, errCode = "", list = ArrayList())
        }

        fun dessertList(TOKEN: String): DessertListModel {
            val code = "<CMD>get_goods_list</CMD><DATA><TOKEN>$TOKEN</TOKEN><CATE>1</CATE><CATE2></CATE2><NAME></NAME></DATA>"
            val Ase256: String = Cbc.encryptCBC(code)
            val EncodeUrl = URLEncoder.encode(Ase256, "UTF-8")
            val resultUrl: String = serverUrl + EncodeUrl
            val url = URL(resultUrl)
            val httpClient: HttpURLConnection = url.openConnection() as HttpURLConnection

            try {
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
                httpClient.disconnect()
                return DessertListModel(connection = false, errCode = "", list = ArrayList())
            }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return DessertListModel(connection = false, errCode = "", list = ArrayList())
        }

        fun menuList(TOKEN: String, category: Int): MenuListModel {
            val code = "<CMD>get_goods_list</CMD><DATA><TOKEN>$TOKEN</TOKEN><CATE>$category</CATE><CATE2></CATE2><NAME></NAME></DATA>"
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
                httpClient.disconnect()
                return Json.menuListJSONParse(decryptedString)
            } else {
                httpClient.disconnect()
                return MenuListModel(connection = false, errCode = "", list = ArrayList())
            } } catch (e: Exception) {
            e.printStackTrace()
            Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
        }
            httpClient.disconnect()
            return MenuListModel(connection = false, errCode = "", list = ArrayList())
        }


        fun menudetail(TOKEN: String, NO: Int): GoodsModel {
            val code = "<CMD>get_goods_detail</CMD><DATA><TOKEN>$TOKEN</TOKEN><NO>$NO</NO><SHOPNO></SHOPNO></DATA>"
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
                    httpClient.disconnect()
                    return Json.menuDetailJSONParse(decryptedString)
                } else {
                    httpClient.disconnect()
                    return GoodsModel(connection = false, errCode = "", no = 0, "", "", "", ""
                        , price = "", 0,"",0,"","","","", "", "", "","",
                    "", "", "", "", "", "")
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return GoodsModel(connection = false, errCode = "", no = 0, "", "", "", ""
                , price = "", 0,"",0,"","","","", "", "", "", "",
            "", "", "", "", "" ,"")
        }


        fun newMenu(TOKEN: String): NewMenuModel {
            val code = "<CMD>get_recommend_goods</CMD><DATA><TOKEN>$TOKEN</TOKEN><PART>n</PART></DATA>"
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
                    httpClient.disconnect()
                    return Json.newMenu(decryptedString)
                } else {
                    httpClient.disconnect()
                    return NewMenuModel(connection = false, errCode = "", list = ArrayList())
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return NewMenuModel(connection = false, errCode = "", list = ArrayList())
        }

        fun seasonMenu(TOKEN: String): SeasonMenuModel {
            val code = "<CMD>get_recommend_goods</CMD><DATA><TOKEN>$TOKEN</TOKEN><PART>s</PART></DATA>"
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
                    httpClient.disconnect()
                    return Json.seaSonMenu(decryptedString)
                } else {
                    httpClient.disconnect()
                    return SeasonMenuModel(connection = false, errCode = "", list = ArrayList())
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return SeasonMenuModel(connection = false, errCode = "", list = ArrayList())
        }

        fun recommandMenu(TOKEN: String): RecommandMenuModel {
            val code = "<CMD>get_recommend_goods</CMD><DATA><TOKEN>$TOKEN</TOKEN><PART>d</PART></DATA>"
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
                    httpClient.disconnect()
                    return Json.recommandMenu(decryptedString)
                } else {
                    httpClient.disconnect()
                    return RecommandMenuModel(connection = false, errCode = "", list = ArrayList())
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return RecommandMenuModel(connection = false, errCode = "", list = ArrayList())
        }

        fun adminRecommandMenu(TOKEN: String): AdminRecommandModel {
            val code = "<CMD>get_recommend_goods</CMD><DATA><TOKEN>$TOKEN</TOKEN><PART>a</PART></DATA>"
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
                    httpClient.disconnect()
                    return Json.adminRecommandMenu(decryptedString)
                } else {
                    httpClient.disconnect()
                    return AdminRecommandModel(connection = false, errCode = "", list = ArrayList())
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return AdminRecommandModel(connection = false, errCode = "", list = ArrayList())
        }



        fun nearbyStore(token:String, latitude: Double, longitude: Double): StoreModel {
            val code = "<CMD>get_shop_list</CMD><DATA><TOKEN>$token</TOKEN><LATITUDE>$latitude</LATITUDE><LONGITUDE>$longitude</LONGITUDE><SHOPNAME></SHOPNAME><PART></PART></DATA>"
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
                    httpClient.disconnect()
                    return Json.nearbyStoreJSONParse(decryptedString)
                } else {
                    return StoreModel(connection = false, errCode = "", list = ArrayList())
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            return StoreModel(connection = false, errCode = "", list = ArrayList())
        }

        fun AllStore(token:String, latitude: Double, longitude: Double, part: String): OrderShopMenuModel {
            val code = "<CMD>get_shop_list</CMD><DATA><TOKEN>$token</TOKEN><LATITUDE>$latitude</LATITUDE><LONGITUDE>$longitude</LONGITUDE><SHOPNAME></SHOPNAME><PART>$part</PART></DATA>"
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
                    httpClient.disconnect()
                    return Json.shopList(decryptedString)
                } else {
                    return OrderShopMenuModel(connection = false, errCode = "", list = ArrayList())
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return OrderShopMenuModel(connection = false, errCode = "", list = ArrayList())
        }


        fun likingMenu(TOKEN: String, CATA:String, DRINK:String, BRAND:String, B_DRINK:String): LikingModel {
            val code = "<CMD>liking</CMD><DATA><CATA>$CATA</CATA><DRINK>$DRINK</DRINK><BRAND>$BRAND</BRAND><B_DRINK>$B_DRINK</B_DRINK><TOKEN>$TOKEN</TOKEN></DATA>"
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
                    httpClient.disconnect()
                    return Json.liking(decryptedString)

                } else {
                    httpClient.disconnect()
                    return LikingModel(connection = false, errCode ="")
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return LikingModel(connection = false, errCode ="")
        }

        fun setBoard(TOKEN: String, category:String, subject: String, content:String): SetBoardModel {
            val code = "<CMD>set_board</CMD><DATA><TOKEN>$TOKEN</TOKEN><PART>custom</PART><CATEGORY>$category</CATEGORY><SUBJECT>$subject</SUBJECT><CONTENT>$content</CONTENT></DATA>"
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
                    httpClient.disconnect()
                    return Json.setBoard(decryptedString)
                } else {
                    httpClient.disconnect()
                    return SetBoardModel(connection = false, errCode = "", idx = "")
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return SetBoardModel(connection = false, errCode = "", idx = "")
        }


        fun BoardList(token:String, PAGE: Int): BoardListModel {
            val code = "<CMD>get_board_list</CMD><DATA><TOKEN>$token</TOKEN><PART>custom</PART><PAGE>$PAGE</PAGE><CATEGORY></CATEGORY></DATA>"
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
                    httpClient.disconnect()
                    return Json.boardList(decryptedString)
                } else {
                    return BoardListModel(connection = false, errCode = "", list = ArrayList(), page = 0)
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return BoardListModel(connection = false, errCode = "", list = ArrayList(), page = 0)
        }

        fun BoardEvent(token:String, PAGE: Int): BoardEventModel {
            val code = "<CMD>get_board_list</CMD><DATA><TOKEN>$token</TOKEN><PART>event</PART><PAGE>$PAGE</PAGE><CATEGORY></CATEGORY></DATA>"
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
                    httpClient.disconnect()
                    return Json.eventBoard(decryptedString)
                } else {
                    return BoardEventModel(connection = false, errCode = "", list = ArrayList(), page = 0)
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return BoardEventModel(connection = false, errCode = "", list = ArrayList(), page = 0)
        }

        fun setOrder(TOKEN: String, SHOPNO:String, GOODS:String, TOTALCNT:String, TOTALPRICE:String, REQUEST: String, COUPON: String,
        COUPONDISCOUNT: String, TOTALDISCOUNT: String, ORIGINPRICE: String, TOTALUMBLER: String, COUPONOWNER: String): SetOrderModel {
            val code = "<CMD>set_order</CMD><DATA><TOKEN>$TOKEN</TOKEN><SHOPNO>$SHOPNO</SHOPNO><GOODS>$GOODS</GOODS><TOTALCNT>$TOTALCNT</TOTALCNT>" +
                    "<TOTALPRICE>$TOTALPRICE</TOTALPRICE><REQUEST>$REQUEST</REQUEST><COUPON>$COUPON</COUPON><COUPONDISCOUNT>$COUPONDISCOUNT</COUPONDISCOUNT>" +
                    "<TOTALDISCOUNT>$TOTALDISCOUNT</TOTALDISCOUNT><ORIGINPRICE>$ORIGINPRICE</ORIGINPRICE>" +
                    "<TOTALTUMBLER>$TOTALUMBLER</TOTALTUMBLER><COUPONOWNER>$COUPONOWNER</COUPONOWNER></DATA>"
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
                    httpClient.disconnect()
                    return Json.setOrder(decryptedString)

                } else {
                    httpClient.disconnect()
                    return SetOrderModel(errCode ="", orderNo = "")
                } } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
            }
            httpClient.disconnect()
            return SetOrderModel(errCode ="", orderNo = "")
        }


        @SuppressLint("LogNotTimber")
        fun cardChoice(strToken: String, file: String): UserImageModel {
            val postBaseURL = "http://dessert39.com/api/index.php/"
            val boundary = "*****" + System.currentTimeMillis().toString() + "*****"
            val lineFeed = "\r\n"
            val twoHyphens = "--"
            val post : String = ""

            val codeString = "<CMD>set_user_img</CMD><DATA><TOKEN>$strToken</TOKEN><USERIMG></USERIMG></DATA>"
            val Ase256: String = Cbc.encryptCBC(codeString)


            val resultURLString: String = postBaseURL
            val resultURL = URL(resultURLString)

            val httpURLConnection: HttpURLConnection =
                resultURL.openConnection() as HttpURLConnection

            httpURLConnection.setRequestProperty("content-type", "multipart/form-data;boundary=$boundary")

            httpURLConnection.requestMethod = "POST"
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            val outPutStream: OutputStream = httpURLConnection.outputStream
            val writer = PrintWriter(OutputStreamWriter(outPutStream, "UTF-8"), true)

            writer.append("--$boundary").append(lineFeed)
            writer.append("Content-Disposition: form-data; name=\"USERIMG\" filename=\"${file}\"")
                .append(lineFeed)
            writer.append("Content-Type: image/jpeg$lineFeed")
            writer.append("Content-Transfer-Encoding: binary$lineFeed")
            writer.append(Ase256).append(lineFeed)
            writer.append("--$boundary--").append(lineFeed)
            writer.flush()

            val inputStreamToFile = FileInputStream(file)
            var bytesRead: Int
            val dataBuffer = ByteArray(1024)
            while (inputStreamToFile.read(dataBuffer).also { bytesRead = it } != -1) {
                outPutStream.write(dataBuffer, 0, bytesRead)
            }

//            val fileInputStream = FileInputStream(file)
//            var bytesAvailable: Int = fileInputStream.available()
//            val maxBufferSize = 1 * 1024 * 1024
//            var bufferSize = bytesAvailable.coerceAtMost(maxBufferSize)
//            var buffer = ByteArray(bufferSize)
//            var bytesRead: Int = fileInputStream.read(buffer, 0, bufferSize)
//
//
//            while (bytesRead > 0) {
//                outPutStream.write(buffer, 0, bufferSize)
//                bytesAvailable = fileInputStream.available()
//                bufferSize = bytesAvailable.coerceAtMost(maxBufferSize)
//                buffer =  ByteArray(bufferSize)
//                bytesRead = fileInputStream.read(buffer, 0, bufferSize)
//            }
//
//            writer.append(lineFeed)
//
//            val posts: Array<String> = post.split("&").toTypedArray()
//            val max = posts.size
//            for (i in 0 until max) {
//                writer.append(twoHyphens + boundary + lineFeed)
//                val kv = posts[i].split("=").toTypedArray()
//                writer.append("Content-Disposition: form-data; name=\"" + kv[0] + "\"" + lineFeed)
//                writer.append("Content-Type: text/plain$lineFeed")
//                writer.append(lineFeed)
////                writer.append(kv[1])
//                writer.append(lineFeed)
//            }
//
//            writer.append(twoHyphens + boundary + twoHyphens + lineFeed)

//            fileInputStream.close()
            outPutStream.flush()
            writer.append("--$boundary--").append(lineFeed)
            writer.flush()

            outPutStream.close()
            writer.close()

            try {
                if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {

                    val streamReader = InputStreamReader(httpURLConnection.inputStream, "EUC-KR")

                    val buffered = BufferedReader(streamReader)
                    val inputContent = StringBuilder()

                    while (true) {
                        val inputLine = buffered.readLine() ?: break
                        inputContent.append(inputLine)
                    }

                    val contentString: String = inputContent.toString()
                    val decryptedString: String = Cbc.decryptCBC(contentString)
                    httpURLConnection.disconnect()

                    return Json.userImage(decryptedString)
                } else {
                    Log.d(LogTag, "httpURLConnection.responseCode = ${httpURLConnection.responseCode}")
                    httpURLConnection.disconnect()
                    return UserImageModel(Connection = false, "", "")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(LogTag,"e.printStackTrace() = ${e.printStackTrace()}")
                httpURLConnection.disconnect()
                return UserImageModel(Connection = false, "", "")
            }

        }


//        private fun convertStreamToString(`is`: InputStream): String? {
//            val reader = BufferedReader(InputStreamReader(`is`))
//            val sb = java.lang.StringBuilder()
//            var line: String? = null
//            try {
//                while (reader.readLine().also { line = it } != null) {
//                    sb.append(line)
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//            } finally {
//                try {
//                    `is`.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }
//            return sb.toString()
//        }
    }

}