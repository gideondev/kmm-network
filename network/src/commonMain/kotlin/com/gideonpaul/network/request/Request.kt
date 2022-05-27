package com.gideonpaul.network.request

data class Request(
    var url: String,
    var method: HttpMethod = HttpMethod.GET,
    var headers: Map<String, String> = emptyMap(),
    var queryParams: Map<String, List<String>> = emptyMap(),
)
