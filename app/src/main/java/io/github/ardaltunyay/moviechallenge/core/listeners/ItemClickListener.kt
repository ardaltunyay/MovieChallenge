package io.github.ardaltunyay.moviechallenge.core.listeners

class ItemClickListener<T>(private val click: (T) -> Unit) {
    fun onClicked(data: T) = click(data)
}