package io.github.ardaltunyay.moviechallenge.di

import io.github.ardaltunyay.moviechallenge.BuildConfig
import io.github.ardaltunyay.moviechallenge.core.network.createBaseRequestInterceptor
import io.github.ardaltunyay.moviechallenge.core.network.createHttpLoggingInterceptor
import io.github.ardaltunyay.moviechallenge.core.network.createOkHttpClient
import io.github.ardaltunyay.moviechallenge.core.network.createWebService
import io.github.ardaltunyay.moviechallenge.core.network.interceptors.NetworkConnectionInterceptor
import io.github.ardaltunyay.moviechallenge.data.remote.api.IMovieApi
import org.koin.dsl.module

val networkModule = module {
    single { createHttpLoggingInterceptor() }
    single { createBaseRequestInterceptor(BuildConfig.API_KEY) }
    single { NetworkConnectionInterceptor(context = get()) }
    single { createOkHttpClient(httpLoggingInterceptor = get(), networkConnectionInterceptor = get(), baseRequestInterceptor = get()) }
    single { createWebService<IMovieApi>(okHttpClient = get(), url = BuildConfig.BASE_URL) }
}