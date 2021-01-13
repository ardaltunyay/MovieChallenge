package io.github.ardaltunyay.moviechallenge.ui.movie_details.model

sealed class MovieDetailsActionState {
    object LoadingState : MovieDetailsActionState()
    data class SetMovieState(val movieDetails: MovieDetailsUIModel) : MovieDetailsActionState()

    fun getVisibility(): Boolean {
        return this is SetMovieState
    }
}

sealed class MovieDetailsClickActions {
    object OnBackClicked : MovieDetailsClickActions()
}