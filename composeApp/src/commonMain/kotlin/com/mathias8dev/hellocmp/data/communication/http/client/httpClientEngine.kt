package com.mathias8dev.hellocmp.data.communication.http.client

import io.ktor.client.engine.HttpClientEngineFactory

expect val httpClientEngine: HttpClientEngineFactory<*>