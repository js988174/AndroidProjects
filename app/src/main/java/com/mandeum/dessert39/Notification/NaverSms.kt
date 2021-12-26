package com.mandeum.dessert39.Notification

import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class NaverSms {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
         fun makeSignature(
            url: String,
            timestamp: String,
            method: String,
            accessKey: String,
            secretKey: String
        ): String {
            val space = " " // one space
            val newLine = "\n" // new line
            val message = StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString()
            val signingKey: SecretKeySpec
            var encodeBase64String: String
            try {
                signingKey = SecretKeySpec(secretKey.toByteArray(charset("UTF-8")), "HmacSHA256")
                val mac: Mac = Mac.getInstance("HmacSHA256")
                mac.init(signingKey)
                val rawHmac: ByteArray = mac.doFinal(message.toByteArray(charset("UTF-8")))
                encodeBase64String = Base64.getEncoder().encodeToString(rawHmac)
            } catch (e: UnsupportedEncodingException) {
                // TODO Auto-generated catch block
                encodeBase64String = e.toString()
            }
            return encodeBase64String
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun sendSMS() {
            val hostNameUrl = "https://sens.apigw.ntruss.com"
            var requestUrl = "/sms/v2/services/"
            val requestUrlType = "/messages"
            val accessKey = "hruN0qYNWYN2Uhewf0Oe"
            val secretKey = "CtWRBF3cHS73v1y3w1luWXl3W3QkuUH6u4ogoIMR"
            val serviceId = "ncp:sms:kr:274406477880:dessert39"
            val method = "POST"
            val timestamp = System.currentTimeMillis().toString() // current timestamp (epoch)
            requestUrl += serviceId + requestUrlType
            val apiUrl = hostNameUrl + requestUrl

            val bodyJson = JSONObject()
            val toJson = JSONObject()
            val toArr = JSONArray()

            //toJson.put("subject","");
            //toJson.put("content","sms test in spring 111");
            toJson.put("to", "01022214906")
            toArr.put(toJson)
            bodyJson.put("type", "SMS")
            //bodyJson.put("contentType","");				l
            //bodyJson.put("countryCode","82");
            bodyJson.put("from", "01022214906")
            //bodyJson.put("subject","");
            bodyJson.put(
                "content",
                "sms test in spring 222"
            )
            bodyJson.put("messages", toArr)

            //String body = bodyJson.toJSONString();
            val body: String = bodyJson.toString()
            println(body)
            try {
                val url = URL(apiUrl)
                val con: HttpURLConnection = url.openConnection() as HttpURLConnection
                con.useCaches = false
                con.doOutput = true
                con.doInput = true
                con.setRequestProperty("content-type", "application/json")
                con.setRequestProperty("x-ncp-apigw-timestamp", timestamp)
                con.setRequestProperty("x-ncp-iam-access-key", accessKey)
                con.setRequestProperty(
                    "x-ncp-apigw-signature-v2",
                    makeSignature(requestUrl, timestamp, method, accessKey, secretKey)
                )
                con.requestMethod = method
                con.doOutput = true
                val wr = DataOutputStream(con.outputStream)
                wr.write(body.toByteArray())
                wr.flush()
                wr.close()
                val responseCode: Int = con.responseCode
                val br: BufferedReader
                println("responseCode $responseCode")
                if (responseCode == 202) { // 정상 호출
                    br = BufferedReader(InputStreamReader(con.inputStream))
                } else { // 에러 발생
                    br = BufferedReader(InputStreamReader(con.errorStream))
                }
                var inputLine: String?
                val response = StringBuffer()
                while (br.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                br.close()
                println(response.toString())
            } catch (e: Exception) {
                println(e)
            }
        }


    }
}