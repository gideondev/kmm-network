package com.gideonpaul.network.request

interface RequestProcessor {
    fun processRequest(request: Request): Request
}
