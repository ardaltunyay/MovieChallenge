package io.github.ardaltunyay.moviechallenge.core.exceptions

import java.io.IOException

class TokenException(private val msg: String? = null) : IOException() {
    override val message: String?
        get() = msg
}