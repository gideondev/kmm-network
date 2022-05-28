package com.gideonpaul.network.response

interface ResponseProcessor {
    fun processResponse(response: Response): Response
}
