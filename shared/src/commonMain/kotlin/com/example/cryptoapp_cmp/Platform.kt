package com.example.cryptoapp_cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform