package com.mathias8dev.hellocmp.data.communication.http

class ApiRequestException(
    val httpStatusCode: Int,
    override val message: String?,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)