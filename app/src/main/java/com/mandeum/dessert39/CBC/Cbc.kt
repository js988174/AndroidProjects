package com.mandeum.dessert39.CBC

import android.util.Base64
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Cbc {

    companion object {
        const val strAESKey = "dI@7ZmVPsozXXss8Yt2upyk62gPSVVnD"
        const val strAESIV = "LoG!R\$hFr\$AHw7yY"


        // 암호화
         fun encryptCBC(byte : String): String {
            val iv = IvParameterSpec(strAESIV.toByteArray())
            val keySpec = SecretKeySpec(strAESKey.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv)
            val crypted = cipher.doFinal(byte.toByteArray())
            val encodedByte = Base64.encode(crypted, Base64.NO_WRAP)
            return String(encodedByte)
        }

        // 복호화
         fun decryptCBC(byte : String): String {
            var decodedByte: ByteArray = Base64.decode(byte, Base64.DEFAULT)
            val iv: AlgorithmParameterSpec = IvParameterSpec(strAESIV.toByteArray())
            val keySpec = SecretKeySpec(strAESKey.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv)
            val output = cipher.doFinal(decodedByte)

            return String(output)


        }
    }
}