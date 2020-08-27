package com.github.songOrderPlugin

import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.message.nextMessage
import net.mamoe.mirai.utils.info


object SongOrderMain : KotlinPlugin() {
    override fun onEnable() {
        logger.info { "Hi, this is a SongOrder Plugin" }
    }

    override fun onLoad() {
        subscribeMessages {
            startsWith("点歌", removePrefix = true, trim = true) {
                var msg = it
                if (it.isEmpty()) {
                    reply("你要点什么歌?")
                    msg = nextMessage { true }.content
                    if (msg in arrayOf("算了, 取消")) {
                        reply("好的")
                        return@startsWith
                    }
                }
                val netEaseMusicLightApp = MusicProvider.netEaseMusicGen(msg)
                if (netEaseMusicLightApp == null) {
                    reply("发送失败")
                } else netEaseMusicLightApp.send()
            }
        }
    }

    override fun onDisable() {
        logger.info { "Bye" }
    }
}