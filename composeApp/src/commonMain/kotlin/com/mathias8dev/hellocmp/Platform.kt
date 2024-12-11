package com.mathias8dev.hellocmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform