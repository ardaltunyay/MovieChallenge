package io.github.ardaltunyay.moviechallenge.data.remote.model.base

data class BaseError(
    val status_code: Int? = null,
    val status_message: String? = null,
    val success: Boolean? = null
)