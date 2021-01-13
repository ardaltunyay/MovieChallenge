package io.github.ardaltunyay.moviechallenge.core.network

import io.github.ardaltunyay.moviechallenge.BuildConfig
import io.github.ardaltunyay.moviechallenge.core.network.interceptors.BaseRequestInterceptor
import io.github.ardaltunyay.moviechallenge.core.network.interceptors.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT_IN_MILLISECONDS = 15000L
private const val CONNECT_TIMEOUT_IN_MILLISECONDS = 15000L

fun createBaseRequestInterceptor(apiKey: String): BaseRequestInterceptor = BaseRequestInterceptor(apiKey)

fun createHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

fun createOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor,
    baseRequestInterceptor: BaseRequestInterceptor
): OkHttpClient =
    with(OkHttpClient.Builder()) {
        connectTimeout(CONNECT_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
        readTimeout(READ_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
        addInterceptor(networkConnectionInterceptor)
        addInterceptor(baseRequestInterceptor)
        if (BuildConfig.DEBUG) {
            addInterceptor(httpLoggingInterceptor)
        }
        build()
    }

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T =
    with(Retrofit.Builder()) {
        baseUrl(url)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
        build()
    }.create(T::class.java)
