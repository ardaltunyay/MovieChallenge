package io.github.ardaltunyay.moviechallenge.core.network.interceptors

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class BaseRequestInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response  {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = with(originalHttpUrl.newBuilder()) {
            addQueryParameter("api_key", apiKey)
            build()
        }

        val requestBuilder = original.newBuilder().url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}