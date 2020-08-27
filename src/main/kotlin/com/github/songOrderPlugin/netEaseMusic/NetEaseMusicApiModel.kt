package com.github.songOrderPlugin.netEaseMusic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetEaseMusicApiModel(
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
        @SerialName("songs")
        val songs: List<Song>
    )

    @Serializable
    data class Song(
        @SerialName("al")
        val al: Al,
        @SerialName("ar")
        val ar: List<Ar>,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
    )

    @Serializable
    data class Al(
        @SerialName("picUrl")
        val picUrl: String
    )

    @Serializable
    data class Ar(
        @SerialName("name")
        val name: String
    )
}
