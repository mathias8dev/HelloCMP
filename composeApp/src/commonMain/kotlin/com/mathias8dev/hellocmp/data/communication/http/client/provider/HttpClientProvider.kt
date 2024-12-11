package com.mathias8dev.hellocmp.data.communication.http.client.provider


import com.mathias8dev.hellocmp.data.communication.http.ApiRoutes
import com.mathias8dev.hellocmp.data.communication.http.client.httpClientEngine
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object HttpClientProvider {
    fun defaultClient(): HttpClient {
        return HttpClient(httpClientEngine) {
            expectSuccess = true // Throw exception if response not in 200..299
            followRedirects = true


            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })

            }

            install(DefaultRequest) {
                url(ApiRoutes.baseUrl)
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "KtorHttpClient", message = message)
                    }
                }
                level = LogLevel.ALL
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }


            install(HttpTimeout) {
                requestTimeoutMillis = 2 * 60 * 1000
                socketTimeoutMillis = 2 * 60 * 1000
                connectTimeoutMillis = 2 * 60 * 1000
            }

            install(HttpRedirect) {
                checkHttpMethod = false
            }

        }
    }

}

