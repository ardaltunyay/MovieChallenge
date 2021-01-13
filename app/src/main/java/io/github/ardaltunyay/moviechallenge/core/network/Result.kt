package io.github.ardaltunyay.moviechallenge.core.network

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T?) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    fun onSuccess(successHandler: (T?) -> Unit): Result<T> = this.also {
        if (this is Success) successHandler(data)
    }

    fun onError(errorHandler: (Exception) -> Unit): Result<T> = this.also {
        if (this is Error) errorHandler(exception)
    }

    fun getOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}