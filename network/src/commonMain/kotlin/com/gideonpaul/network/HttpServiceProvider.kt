package com.gideonpaul.network

import com.gideonpaul.network.request.Request

interface HttpServiceProvider {
    suspend fun <T : Any?> sendRequest(
        request: Request,
        completionHandler: (String) -> Unit
    )
}
