package com.github.songOrderPlugin

import com.github.songOrderPlugin.netEaseMusic.NetEaseFormData
import com.github.songOrderPlugin.netEaseMusic.NetEaseMusicApiModel
import com.github.songOrderPlugin.netEaseMusic.NetEaseMusicLightApp
import io.ktor.client.request.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.mamoe.mirai.message.data.LightApp
import java.net.URLEncoder

@Suppress("BlockingMethodInNonBlockingContext")  // 哭了
object MusicProvider {
    suspend fun netEaseMusicGen(searchMusicName: String): LightApp? {
        val preFormData =
            """{"hlpretag":"<span class=\"s-fc7\">","hlposttag":"</span>","s":"$searchMusicName","type":"1","offset":"0","total":"true","limit":"1","csrf_token":""}"""

        val params = URLEncoder.encode( // 阻塞方法
            NetEaseFormData.encrypt(preFormData),
            "utf-8"
        )
        val url =
            """https://music.163.com/weapi/cloudsearch/get/web?csrf_token=&params=${params}&encSecKey=${NetEaseFormData.encSecKey}"""

        val client = KtorClient.getInstance() ?: return null

        val model = client.post<NetEaseMusicApiModel>(url) {
            header("Host", "music.163.com")
            header("X-Real-IP", "bilibili.com")
            header("X-Forwarded-For", "bilibili.com")
        }

        val ctime = System.currentTimeMillis() / 1000
        val song = model.result.songs.first()
        val musicName = song.name
        val musicId = song.id
        val preview = song.al.picUrl
        val desc = song.ar.first().name

        val music = NetEaseMusicLightApp(
            "com.tencent.structmsg",
            NetEaseMusicLightApp.Config(
                true,
                ctime,
                true,
                "114514",
                "normal"
            ),
            "音乐",
            NetEaseMusicLightApp.Extra(
                1,
                100495085,
                6863003740196404000
            ),
            NetEaseMusicLightApp.Meta(
                NetEaseMusicLightApp.MusicX(
                    "",
                    "",
                    1,
                    100495085,
                    desc, // singer
                    "https://y.music.163.com/m/song/$musicId",
                    "http://music.163.com/song/media/outer/url?id=$musicId",
                    preview,
                    "",
                    "0",
                    "",
                    "网易云音乐 via SongOrder",
                    musicName
                )
            ),
            "[分享]$musicName via SongOrder",
            "0.0.0.1",
            "music"
        )
        val json = Json.encodeToString(music)
        return LightApp(json)
    }
}
