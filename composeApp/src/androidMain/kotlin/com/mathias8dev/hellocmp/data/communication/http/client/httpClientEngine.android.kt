package com.mathias8dev.hellocmp.data.communication.http.client

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual val httpClientEngine: HttpClientEngineFactory<*>
    get() = OkHttp