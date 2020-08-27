package com.github.songOrderPlugin.netEaseMusic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetEaseMusicLightApp(
    @SerialName("app")
    val app: String,
    @SerialName("config")
    val config: Config,
    @SerialName("desc")
    val desc: String,
    @SerialName("extra")
    val extra: Extra,
    @SerialName("meta")
    val meta: Meta,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("ver")
    val ver: String,
    @SerialName("view")
    val view: String
) {
    @Serializable
    data class Config(
        @SerialName("autosize")
        val autosize: Boolean,
        @SerialName("ctime")
        val ctime: Long,
        @SerialName("forward")
        val forward: Boolean,
        @SerialName("token")
        val token: String,
        @SerialName("type")
        val type: String
    )

    @Serializable
    data class Extra(
        @SerialName("app_type")
        val appType: Int,
        @SerialName("appid")
        val appid: Int,
        @SerialName("msg_seq")
        val msgSeq: Long
    )

    @Serializable
    data class Meta(
        @SerialName("music")
        val music: MusicX
    )

    @Serializable
    data class MusicX(
        @SerialName("action")
        val action: String,
        @SerialName("android_pkg_name")
        val androidPkgName: String,
        @SerialName("app_type")
        val appType: Int,
        @SerialName("appid")
        val appid: Int,
        @SerialName("desc")
        val desc: String,
        @SerialName("jumpUrl")
        val jumpUrl: String,
        @SerialName("musicUrl")
        val musicUrl: String,
        @SerialName("preview")
        val preview: String,
        @SerialName("source_icon")
        val sourceIcon: String,
        @SerialName("sourceMsgId")
        val sourceMsgId: String,
        @SerialName("source_url")
        val sourceUrl: String,
        @SerialName("tag")
        val tag: String,
        @SerialName("title")
        val title: String
    )
}