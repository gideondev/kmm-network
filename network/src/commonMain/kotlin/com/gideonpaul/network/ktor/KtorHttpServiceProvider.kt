package com.gideonpaul.network.ktor

import com.gideonpaul.network.HttpServiceProvider
import com.gideonpaul.network.request.HttpMethod
import com.gideonpaul.network.request.Request
import com.gideonpaul.network.request.RequestProcessor
import com.gideonpaul.network.response.ResponseProcessor
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class KtorHttpServiceProvider(
    private val scope: CoroutineScope,
    private val requestProcessors: MutableList<RequestProcessor> = mutableListOf(),
    private val responseProcessors: MutableList<ResponseProcessor> = mutableListOf()
) : HttpServiceProvider(
    requestProcessors = requestProcessors,
    responseProcessors = responseProcessors
) {
    var client: HttpClient

    init {
        client = getNetworkClient()
    }

    private fun getNetworkClient(): HttpClient {
        return HttpClient() {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }

            install(ContentNegotiation) {
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            }
        }
    }

    override suspend fun <T : Any?> sendRequest(
        request: Request,
        completionHandler: (String) -> Unit
    ) {
        processRequestPipeline(request)

        when (request.method) {
            HttpMethod.GET -> {
                scope.launch {
                    val response = client.request {
                        url {
                            takeFrom(request.url)

                            request.queryParams.forEach {
                                parameters.appendAll(it.key, it.value)
                            }
                        }

                        headers {
                            request.headers.forEach {
                                append(it.key, it.value)
                            }
                        }

                        method = io.ktor.http.HttpMethod.Get
                    }

                    completionHandler(response.bodyAsText())
                }
            }

            HttpMethod.POST -> TODO()
            HttpMethod.PUT -> TODO()
            HttpMethod.PATCH -> TODO()
            HttpMethod.DELETE -> TODO()
            HttpMethod.HEAD -> TODO()
            HttpMethod.OPTIONS -> TODO()
        }
    }
}
