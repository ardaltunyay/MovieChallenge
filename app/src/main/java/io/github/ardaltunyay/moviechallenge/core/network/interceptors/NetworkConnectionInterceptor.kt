package io.github.ardaltunyay.moviechallenge.core.network.interceptors

import android.content.Context
import io.github.ardaltunyay.moviechallenge.R
import io.github.ardaltunyay.moviechallenge.core.exceptions.NoInternetException
import io.github.ardaltunyay.moviechallenge.core.extensions.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    private val applicationContext
        get() = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!applicationContext.isInternetAvailable) {
            throw NoInternetException(msg = context.getString(R.string.error_internet_connection))
        }
        return chain.proceed(chain.request())
    }
}