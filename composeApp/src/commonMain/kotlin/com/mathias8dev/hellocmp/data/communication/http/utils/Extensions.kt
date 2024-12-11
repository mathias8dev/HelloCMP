package com.mathias8dev.hellocmp.data.communication.http.utils


import io.github.aakira.napier.Napier
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse


suspend inline fun <reified T> HttpResponse.toDefaultApiResponse(): T? {
    return try {
        Napier.d("Converting to body")
        this.body()
    } catch (e: NoTransformationFoundException) {
        e.printStackTrace()
        null
    }
    /*if (status.value in 200..299)
        return this.body()
    throw ApiRequestException(
        httpStatusCode = status.value,
        message = bodyAsText(),
    )*/
}

