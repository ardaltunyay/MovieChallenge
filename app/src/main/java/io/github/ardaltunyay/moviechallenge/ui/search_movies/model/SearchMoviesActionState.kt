package io.github.ardaltunyay.moviechallenge.ui.search_movies.model

import io.github.ardaltunyay.moviechallenge.R

sealed class SearchMoviesActionState {
    object IdleState : SearchMoviesActionState()
    object MovieListState : SearchMoviesActionState()
    object EmptyListState : SearchMoviesActionState()

    fun getVisibility(): Boolean {
        return this !is MovieListState
    }

    fun getIcon(): Int {
        return when (this) {
            is IdleState -> R.drawable.ic_search
            is EmptyListState -> R.drawable.ic_error
            else -> 0
        }
    }

    fun getMessage(): Int {
        return when (this) {
            is IdleState -> R.string.search_movies_idle_state
            is EmptyListState -> R.string.search_movies_empty_list_state
            else -> R.string.empty
        }
    }

}