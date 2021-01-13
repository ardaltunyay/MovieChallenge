package io.github.ardaltunyay.moviechallenge.core.network

import com.google.gson.Gson
import io.github.ardaltunyay.moviechallenge.core.exceptions.ServerException
import io.github.ardaltunyay.moviechallenge.core.exceptions.TokenException
import io.github.ardaltunyay.moviechallenge.data.remote.model.base.BaseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> =
    try {
        val response = apiCall()

        if (response.isSuccessful) {
            Result.Success(response.body())
        } else {

            val baseError = withContext(Dispatchers.IO) {
                try {
                    Gson().fromJson(response.errorBody()?.string(), BaseError::class.java)
                } catch (ex: Exception) {
                    null
                }
            }

            val exception = when (response.code()) {
                401 -> {
                    TokenException(msg = baseError?.status_message)
                }
                in 500..599 -> {
                    ServerException()
                }
                else -> {
                    IOException()
                }
            }
            Result.Error(exception)
        }
    } catch (ex: Exception) {
        Result.Error(exception = ex)
    }
