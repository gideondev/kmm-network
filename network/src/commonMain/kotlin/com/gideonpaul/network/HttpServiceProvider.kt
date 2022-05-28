package com.gideonpaul.network

import com.gideonpaul.network.request.Request
import com.gideonpaul.network.request.RequestProcessor
import com.gideonpaul.network.response.Response
import com.gideonpaul.network.response.ResponseProcessor

abstract class HttpServiceProvider(
    private val requestProcessors: MutableList<RequestProcessor> = mutableListOf(),
    private val responseProcessors: MutableList<ResponseProcessor> = mutableListOf()
) {
    abstract suspend fun <T : Any?> sendRequest(
        request: Request,
        completionHandler: (String) -> Unit
    )

    fun processRequestPipeline(request: Request) {
        for (processor in requestProcessors) {
            processor.processRequest(request)
        }
    }

    fun processResponsePipeline(response: Response) {
        for (processor in responseProcessors) {
            processor.processResponse(response)
        }
    }

    fun addToRequestProcessor(processor: RequestProcessor) {
        requestProcessors.add(processor)
    }

    fun addToResponseProcessor(processor: ResponseProcessor) {
        responseProcessors.add(processor)
    }
}
