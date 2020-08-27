package com.github.songOrderPlugin.netEaseMusic

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object NetEaseFormData {
    const val encSecKey =
        """96082b986b9f636e80c4de5868d9798cd4f5008d09d19c39c21817d36b3df39719a9c6d367e249eedba216ce536e839265edc6e1cc5486db3f9545e5c560f329476cf9bb962a3ef63c4ae48c08df1aac1244f056aa1a356becc10bd475bd95b80442d17515070f50b7730d43c9db00a151a0d530786d336767df354ab9189e50"""

    fun encrypt(value: String): String {
        val fir = enc(value, "0CoJUm6Qyw8W8jud")
        return enc(fir, "9cxqkYv1WsSmRWZ1")
    }

    private fun enc(value: String, password: String): String {
        val initVector = "0102030405060708"
        val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
        val sKeySpec = SecretKeySpec(password.toByteArray(charset("UTF-8")), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv)
        val encrypted = cipher.doFinal(value.toByteArray())
        return Base64.getEncoder().encodeToString(encrypted)
    }
}