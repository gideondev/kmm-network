package com.gideonpaul.network.response

data class Response(
    val statusCode: HttpStatusCode,
    val httpProtocolVersion: Int,
    val requestTime: Long,
    val responseTime: Long,
)