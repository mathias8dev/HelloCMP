package com.mathias8dev.hellocmp.injection.modules

import com.mathias8dev.hellocmp.data.communication.http.client.provider.HttpClientProvider
import io.ktor.client.HttpClient
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single


@Module
class HttpClientModule {


    @Single
    @Named("DefaultClient")
    fun defaultClient(): HttpClient = HttpClientProvider.defaultClient()


}