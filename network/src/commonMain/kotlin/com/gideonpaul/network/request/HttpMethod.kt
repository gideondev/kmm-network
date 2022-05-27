package com.gideonpaul.network.request

enum class HttpMethod(val description: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS");

    fun fromString(methodAsString: String): HttpMethod {
        return when (methodAsString) {
            "GET" -> GET
            "POST" -> POST
            "PUT" -> PUT
            "PATCH" -> PATCH
            "DELETE" -> DELETE
            "HEAD" -> HEAD
            "OPTIONS" -> OPTIONS
            else -> throw IllegalArgumentException()
        }
    }
}