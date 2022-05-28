package com.gideonpaul.network.request

interface RequestPipeline {
    fun processRequest(request: Request): Request
}
