package com.github.songOrderPlugin

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json


object KtorClient {
    private var singleton: HttpClient? = null

    fun getInstance(): HttpClient? {
        if (singleton == null) {
            synchronized(KtorClient::class.java) {
                if (singleton == null) {
                    singleton = HttpClient {
                        install(JsonFeature) {
                            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                                ignoreUnknownKeys = true
                                isLenient = true
                            })
                        }
                    }
                }
            }
        }
        return singleton
    }
}